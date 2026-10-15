# Individual Sketch ��� Week 8 Round 1
**Student:** Kaashif Khan
**Date:** 10/15/2026

---

## 1. Tonight's Prompt

**UC-2: Complete an Order**

**Step 1: How each class gets what it calls.** For every solid arrow in your diagram that goes from one class to another class, one row. Skip arrows from the actor. If one class calls the same other class more than once, that's still one row.

**How it gets it** is exactly one of these five values:

- `handed in on the call`: the object is a parameter of the method that uses it. Every call can hand in a different one.
- `handed in when built`: the object is a parameter of the class's constructor, and the class keeps it in a field.
- `handed back by a call`: the object is what another method returned, like the `Book` that `Catalog.findByTitle()` returns.
- `makes its own`: the class creates the object itself with `new`.
- `reaches for it`: the class gets the object from anywhere in the program, through a `static` field or a `static` method on some class, and nobody hands it in.

**Where that object comes from** says who hands it in, which call hands it back, or who creates it. For `reaches for it`, name the `static` field or method it reaches through, like `Catalog.current`.

| Class | Calls | How it gets it | Where that object comes from |
|---|---|---|---|
| `OrderManager` | `Order` | handed back by a call | `OrderManager.getNextOrder()` returns the oldest `Order` from the active orders list. OrderManager then calls `markCompleted()` on that order. |
| `OrderManager` | `Inventory` | handed in when built | The code that sets up the system creates or obtains the shared `Inventory` object and passes it to the `OrderManager` constructor, which stores it in a field. | 

**Step 2: The constructors.** For every class that appears anywhere in your step 1 table, in either column, write its Java constructor. Signature only, one line each, in one code block:

- Every `handed in when built` row for a class becomes a parameter of that class's constructor.
- Add any plain values the class needs in order to exist, like a name or a price.
- A class that needs nothing handed in has an empty constructor, like `Catalog()` above.
- No bodies, and no other methods.

```java
public OrderManager(Inventory inventory)
public Order(Customer customer)
public Inventory()
```
---

## 2. What I'm Not Sure About

I'm not sure about how OrderManager should get access to Inventory. I chose to have Inventory handed in when OrderManager is built because both classes need to work together when completing an order, but I'm not sure if our group would prefer another way of sharing the same Inventory object.
