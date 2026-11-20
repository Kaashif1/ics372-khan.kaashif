# Individual Sketch - Week 13 Round 1
**Student:** Kaashif Khan  
**Date:** 11/19/2025    

---

## 1. Tonight's Prompt

**Step 1: What 3.7 would cost.** Requirement 3.7 says that if the manager isn't in, a barista can add a menu item. Suppose the barista's screen gets the same Add Item form the manager's screen has, so `BaristaController` gets a method `onAddItem(String name, String price, String ingredientNames)` that does exactly what `ManagerController.onAddItem` does.

Don't write it. Work out what it would take, in two parts.

(a) Under the heading `New fields:`, name every model class `BaristaController` would have to hold as a field that it doesn't hold now, or write `none`.

(b) Every `if` statement in `ManagerController.onAddItem` that `BaristaController.onAddItem` would have to copy, in a table with exactly these two columns, one row for each `if`:

| What the `if` checks | The rule about the business it keeps |
|---|---|
| `copies == 0` | a book with no copies left can't be checked out |

The example row is from the textbook's library system, not the case. It shows only the shape.

- **What the `if` checks** is the condition inside the `if`'s parentheses, copied from the case.
- Under the table, write the total: `Total: N copied`.

**New fields:**

- `Menu` — `BaristaController` already holds an `Inventory`, but it would also need access to the menu to check names and add items.

| What the `if` checks | The rule about the business it keeps |
|---|---|
| `priceInCents <= 0` | A menu item's price must be more than zero. |
| `menu.find(name) != null` | An item cannot be added if its name is already on the menu. |
| `ingredient == null` | Each ingredient used in a menu item must already exist in inventory. |

**Total: 3 copied**


**Step 2: Your fix.** Change the case so that `BaristaController` can add a menu item **without copying any `if` from `ManagerController`**, and so that **a test could check each of those rules without building a controller or a screen.**

Write `BaristaController` whole, in Java, in one code block, with its `onAddItem` method. Put every other class you added or changed in the same block, under it. Any approach you can make work is fine. If you're not sure your idea compiles, write it anyway and put a comment on the line you're unsure of.

```java
// BaristaController.java (changed)
public class BaristaController {
    private final Inventory inventory;
    private final BaristaScreen screen;
    private final MenuItemService menuItemService;

    public BaristaController(Inventory inventory, BaristaScreen screen,
                             MenuItemService menuItemService) {
        this.inventory = inventory;
        this.screen = screen;
        this.menuItemService = menuItemService;
    }

    public void onMarkOut(String ingredientName) {
        Ingredient ingredient = inventory.find(ingredientName);
        if (ingredient == null) {
            screen.showMessage("We don't stock " + ingredientName + ".");
            return;
        }

        ingredient.markOut();
        screen.showMessage(ingredientName + " is marked out.");
    }

    public void onAddItem(String name, String price, String ingredientNames) {
        String message = menuItemService.addItem(name, price, ingredientNames);
        screen.showMessage(message);
    }
}

// ManagerController.java (changed)
public class ManagerController {
    private final Menu menu;
    private final ManagerScreen screen;
    private final MenuItemService menuItemService;

    public ManagerController(Menu menu, Inventory inventory, ManagerScreen screen) {
        this.menu = menu;
        this.screen = screen;
        this.menuItemService = new MenuItemService(menu, inventory);
    }

    public void onAddItem(String name, String price, String ingredientNames) {
        String message = menuItemService.addItem(name, price, ingredientNames);
        screen.showMessage(message);
        if (message.equals(name + " is on the menu.")) {
            showMenu();
        }}

    public void onChangePrice(String name, String price) {
        int priceInCents = (int) Math.round(Double.parseDouble(price) * 100);
        if (priceInCents <= 0) {
            screen.showMessage("The price must be more than zero.");
            return;
        }

        MenuItem item = menu.find(name);
        if (item == null) {
            screen.showMessage(name + " isn't on the menu.");
            return;
        }

        item.setPriceInCents(priceInCents);
        showMenu();
    }

    private void showMenu() {
        List<String> lines = new ArrayList<>();
        for (MenuItem item : menu.getItems()) {
            lines.add(item.getName() + "  "
                + String.format("$%.2f", item.getPriceInCents() / 100.0));
        }
        screen.showMenu(lines);
    }}

// MenuItemService.java (new class in model)
public class MenuItemService {
    private final Menu menu;
    private final Inventory inventory;

    public MenuItemService(Menu menu, Inventory inventory) {
        this.menu = menu;
        this.inventory = inventory;
    }

    public String addItem(String name, String price, String ingredientNames) {
        int priceInCents = (int) Math.round(Double.parseDouble(price) * 100);
        if (priceInCents <= 0) {
            return "The price must be more than zero.";
        }

        if (menu.find(name) != null) {
            return name + " is already on the menu.";
        }

        List<Ingredient> recipe = new ArrayList<>();
        for (String ingredientName : ingredientNames.split(",")) {
            Ingredient ingredient = inventory.find(ingredientName.trim());
            if (ingredient == null) {
                return "We don't stock " + ingredientName.trim() + ".";
            }
            recipe.add(ingredient);
        }

        menu.add(new MenuItem(name, priceInCents, recipe));
        return name + " is on the menu.";
    }
}
```

---

## 2. What I'm Not Sure About

What I'm least sure about is having `MenuItemService` return a message string and having the manager controller check that message to decide when to refresh the menu. I'd test adding valid and invalid items from both controllers to see whether they display the right messages and only add valid items.

---

**Commit this file before group discussion begins.**