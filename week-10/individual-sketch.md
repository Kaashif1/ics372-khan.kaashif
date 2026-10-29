# Individual Sketch — Week 10 Round 1
**Student:** Kaashif Khan
**Date:** 10/29/2026

---

## 1. Tonight's Prompt

### Step 1: The method

Write the whole body of one method, in Java, in one code block:

- **UC-2:** the method that uses up the ingredients for every item on the order, including the ingredients its customizations add (step 5).
- **UC-3:** the method that counts how many of each menu item sold today (steps 2 and 3).

Write it against your group's classes as they are now in `src/main/java`. If you need a method that doesn't exist yet, write the call anyway, and put a comment on that line naming the class it belongs to. Don't change any files in the repo; this lives in your sketch only.

**UC-3 Group UC-M7: — SalesReport.runSalesReport()**

```java
public void runSalesReport(LocalDate date) {
    List<Order> daysOrders = orderManager.getCompletedOrders(date);
    HashMap<MenuItem, Integer> counts = new HashMap<>();
    int total = 0;

    for (Order order : daysOrders) {
        for (OrderItem orderItem : order.getItems()) {
            MenuItem menuItem = orderItem.getMenuItem();
            int quantity = orderItem.getQuantity();

            if (counts.containsKey(menuItem)) {
                int newCount = counts.get(menuItem) + quantity;
                counts.put(menuItem, newCount);
            } else {
                counts.put(menuItem, quantity);
            }

            total += orderItem.getPrice() * quantity;
        }
    }

    this.itemCounts = counts;
    this.total = total;
}
```

### Step 2: The walk table

One row for every collection your method walks that belongs to **another** class: a list, an array, a set or a map. If your method walks the same class's collection twice, that's still one row.

Exactly these four columns:

| Walks it | Whose collection | What it gets | Could it change it? |
|---|---|---|---|
| `SalesReport` | `OrderManager`'s orders | a copy | no |
| `SalesReport` | `Order`'s items | the collection itself | yes |

**What it gets** is exactly one of these five values:

- `the collection itself`: the owner hands over its own field, like `getHolds()` whose body is `return holds;`. This is also the value when the walker made the list and handed it to the owner, because both now hold the same list.
- `a copy`: a new collection with the same objects in it, like `List.copyOf(holds)` or `new ArrayList<>(holds)`.
- `a read-only view`: the owner's own collection wrapped so that any change throws an exception, like `Collections.unmodifiableList(holds)`.
- `an iterator`: the owner hands out something that walks its collection, and nothing else. The owner implements `Iterable<Hold>`, so the walker writes `for (Hold hold : member)`.
- `the answer`: the owner walks its own collection and hands back only the result, like `member.countOverdue(today)`. Your method then doesn't walk it at all, but the row still goes in.

**Could it change it?** `yes` if your method, or anything holding what your method got, could add to the owner's collection, take something out of it, or reorder it. `no` if it couldn't.

---

## 2. What I'm Not Sure About

The row that I was least sure about was SalesReport accessing OrderManager's orders. The getCompletedOrders() method creates a new list containing the orders completed on the selected date, so I chose "a copy." However, I wasn't completely sure because the new list still contains references to the original Order objects. I also noticed that Order.getItems() returns its original list, so another class could directly change the items in an order.

---