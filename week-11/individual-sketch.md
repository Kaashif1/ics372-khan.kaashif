# Individual Sketch — Week 11 Round 1
**Student:** Kaashif Khan 
**Date:** 11/5/2026

---

## 1. Tonight's Prompt

**Step 1: What the barista's screen costs.**

Don't write the screen. Work out what adding it would do to the case as it stands.

In a table with exactly these three columns, one row for every file in the case that would have to change:

| File | What changes in it | Why it has to change |
|---|---|---|
| `OrderDesk.java` | Add a field for the barista screen, change the constructor, and call the screen when orders are placed or completed. | Right now OrderDesk calls each screen directly, so it needs to know about the new screen. |
| `OrderQueue.java` | Add a method that returns the waiting orders in order. | The barista screen needs to display every waiting order, oldest first. |
| `PlaceOrderTest.java` | Update the OrderDesk constructor call to pass in the new screen. | Adding another required constructor parameter would break the existing test. |
| `CompleteOrderTest.java` | Update the OrderDesk constructor call to pass in the new screen. | This test also creates an OrderDesk using the original constructor. |

`Total: 4 files`. 

**Step 2: Your fix.**

Change OrderDesk, and anything else in the case you need to, so that after your change, adding the barista's screen, or any screen after it, changes no file you already have except `ShopSetup`.

Write the changed `OrderDesk` whole, in Java, in one code block. Put any other class you added or changed in the same block, under it. Any approach you can make work is fine. If you're not sure your idea compiles, write it anyway and put a comment on the line you're unsure of.

```java
// OrderDesk.java
package edu.metrostate.oodi.brewandbyte.model;

import edu.metrostate.oodi.brewandbyte.view.ManagerWarnings;
import edu.metrostate.oodi.brewandbyte.view.PickupBoard;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrderDesk {
    private final OrderQueue queue;
    private final Inventory inventory;
    private final SalesLedger ledger;
    private final ManagerWarnings warnings;
    private final PickupBoard pickup;
    private final List<Consumer<List<Order>>> queueScreens = new ArrayList<>();

    public OrderDesk(OrderQueue queue, Inventory inventory, SalesLedger ledger,
                     ManagerWarnings warnings, PickupBoard pickup) {
        this.queue = queue;
        this.inventory = inventory;
        this.ledger = ledger;
        this.warnings = warnings;
        this.pickup = pickup;
    }

    public void addQueueScreen(Consumer<List<Order>> screen) {
        queueScreens.add(screen);
        screen.accept(queue.copyQueue());
    }

    private void updateQueueScreens() {
        List<Order> waiting = queue.copyQueue();

        for (Consumer<List<Order>> screen : queueScreens) {
            screen.accept(waiting);
        }
    }

    public void place(Order order) {
        queue.add(order);
        updateQueueScreens();
    }

    public void completeNext(LocalDateTime now) {
        Order order = queue.next();
        order.markComplete(now);

        for (Ingredient low : inventory.useUp(order)) {
            warnings.lowStock(low);
        }

        queue.remove(order);
        ledger.record(order);
        pickup.call(order.getConfirmationNumber());

        updateQueueScreens();
    }}

// OrderQueue.java
package edu.metrostate.oodi.brewandbyte.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class OrderQueue {
    private final Deque<Order> orders = new ArrayDeque<>();

    public void add(Order order) {
        orders.addLast(order);
    }

    public Order next() {
        return orders.peekFirst();
    }

    public void remove(Order order) {
        orders.remove(order);
    }

    public List<Order> copyQueue() {
        return List.copyOf(orders);
    }

    public int size() {
        return orders.size();
    }}
```

With the change I implemented, shopSetup can register a new screen using `addQueueScreen()` without changing OrderDesk's constructor or either existing test. The screen receives the current queue when registered and after every placement or completion.

---

## 2. What I'm Not Sure About

I'm not sure whether using Consumer<List<Order>> is the best way to notify screens. It works for a screen that displays the order queue but another type of screen might need different information. I'd test this by adding a second screen and checking whether it can receive the updates it needs without modifying OrderDesk again.

---

**Commit this file before group discussion begins.**
