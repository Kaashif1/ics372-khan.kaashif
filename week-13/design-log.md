# Design Log: Week 13
**Student:** Kaashif Khan  
**Group:** 1
**Date:** 11/20/2026

**Last week's feedback:** Full marks.  

---

## Part 1: The Problem

> Tonight wasn't the activity you did. In two or three sentences, say what the problem actually was.

The problem was that our controllers could become too dependent on individual model classes and end up handling business rules that don't belong in them. We needed a way for different screens to perform actions like adding menu items and changing prices without duplicating those rules or needing to know how the entire model works.

---

## Part 2: What We Ended With

> Describe what your group ended with, as of your group's last commit tonight, clearly enough that someone who wasn't in your room could rebuild it. Name the file it's in.

Our group ended with a Shop class in model/Shop.java that acts as a facade between the controllers and the rest of the model. It holds references to Menu, Inventory, OrderManager, and SalesReport, which are passed into its constructor. We also created ManagerController.java in the controller package to handle the manager's window. When the manager enters an item's name and a price of 0 and presses Change price, ManagerController.onChangePrice() reads the inputs and converts the price into cents. It then calls Shop.updateMenuItemPrice(), which uses Menu.findItem() to find the item before calling MenuItem.setPrice(). Since the price is 0, MenuItem.setPrice() throws an IllegalArgumentException with the message "Price must be greater than zero." The controller catches the exception and displays that message through messageLabel. We also kept the duplicate menu item rule inside Menu.addItem() because Menu owns the list of items. While MenuItem handles price validation because it stores the price.  

---

## Part 3: One Decision That Moved or Held

> Take one decision you made alone in your sketch. Say what you chose and why it looked right at the time. Then say what happened to it tonight: what changed it, or what you heard and held against.

In my individual sketch, I created a MenuItemService class to handle adding menu items. I chose this because the original case would have required BaristaController to copy three if statements from ManagerController, which could lead to duplicated business rules. By moving those checks into one class, both controllers could use the same logic. During the group work we ended up using a facade called Shop instead of a service specifically for adding menu items. We also kept the individual rules inside the classes that own the relevant data such as Menu for duplicate names and MenuItem for invalid prices. I think this was a better separation of responsibilities because Shop can coordinate different operations without having to implement every business rule itself.

---

## Part 4: Argue the Other Side

> Take the option your group didn't choose and make its strongest case, fairly enough that someone who wanted it would recognize their own argument. Then say what beat it, or that nothing did.

An alternative would have been to let the controllers communicate directly with Menu, Inventory, and the other model classes instead of introducing Shop. This would be simpler because we wouldn't need an additional class just to pass method calls along. For a small application with only a couple of screens, directly accessing the model could be easier to understand and require less code. However as our application grows and more screens are added, each controller could end up depending on several model classes. Changes to the model might then require changes across multiple controllers. We decided the facade was worth the extra class because it provides one main entry point for the controllers. I think the direct approach would make more sense if the application stayed small.

---

## Part 5: What Could Break

> Name one specific thing in your design that could turn out wrong, the situation that would expose it, and what you'd need to see to find out.

One thing I'm unsure about is Shop.updateMenuItemPrice() knowing that it has to find a MenuItem and then call setPrice() on it. Even though the price validation is inside MenuItem, the facade still knows the steps needed to change a price. This could become a problem if changing an item's price later involves more rules such as checking whether the item can currently be modified. We might need to move the entire price changing operation into Menu so Shop can delegate it with one method call. I'd test this by adding another rule for changing prices and seeing whether it can be implemented in the model without modifying Shop or either controller.   