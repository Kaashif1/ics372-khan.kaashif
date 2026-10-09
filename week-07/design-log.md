# Design Log — Week 7
### ICS 372 Object Oriented Programming | Fall Semester
**Student:** Kaashif Khan
**Group:** 1
**Date:** 10/8/2026
**Topic:** Design Methodology

---

## Part 1 — The Problem

The problem we worked on was figuring out which classes should be responsible for different tasks. We had to take the use cases we worked on and compare them to our existing class diagram to see whether the classes actually had the methods and information needed to carry out those tasks. We also had to decide what changes would make the design easier to maintain.

---

## Part 2 — Your Design Decision

Our group worked on assigning responsibilities to classes based on the information they already held. We discussed keeping Inventory responsible for managing ingredient quantities and OrderManager responsible for managing the active order queue. We also considered moving responsibilities involving individual menu items to OrderItem, since it stores the selected item, quantity, and customizations. In our group artifact we included proposed methods such as Inventory.useIngredients(order: Order) to deduct ingredients and OrderManager.getNextOrder() to retrieve the oldest order. We also discussed possibly adding classes like BaristaDashboard and CustomerDashboard to separate user interactions from the rest of the system. Our decisions are documented in docs/week-07/group-artifact.md.

---

## Part 3 — How You Got There

I started with my individual sketch for UC-2, where the barista completes an order. While creating my sequence diagram, I noticed that our class model was missing several methods needed to finish the main flow. For example, OrderManager had a method to retrieve active orders but it didn't have a method specifically for finding the oldest order. Inventory also had methods for adding and removing ingredients but nothing for deducting the quantities used by an order. At first, I was adding methods wherever the sequence diagram needed them. During the group discussion we had to think more carefully about whether those methods belonged to the correct classes. Rule 2, which says that the class holding the necessary data should do the job helped me understand why Inventory should handle ingredient deductions while OrderManager should handle the queue.

---

## Part 4 — The Road Not Taken

One alternative was to let OrderManager handle most of the work involved in completing an order, including calculating ingredients and updating the inventory. This might've made the process more simple initially because OrderManager already coordinates active orders. However, I think this would give OrderManager too many unrelated responsibilities. Inventory already handles stock information, so it makes more sense to keep ingredient deductions in that class. 

---

## Part 5 — What You're Uncertain About

I'm not sure how we should represent the base ingredients required for each menu item. OrderItem knows the selected item and its customizations, but it can't calculate the exact ingredients used unless the base recipe is stored somewhere. I'm also concerned about our OrderManager design. Its activeOrders field currently uses List<OrderItem>, even though it should manage complete orders. I think changing it to List<Order> would make more sense. With more time, I'd review how these classes interact to make sure that completing an order updates inventory and the order queue without duplicating responsibilities.

---

## Word Count: 396 words

---