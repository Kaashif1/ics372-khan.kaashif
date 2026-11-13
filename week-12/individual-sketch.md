# Individual Sketch — Week 12 Round 1
**Student:** Kaashif Khan 
**Date:** 11/12/2026

---

## 1. Tonight's Prompt

**Step 1: What 50 cents an item would cost.** Requirement 2.8 says *loyalty members get their discount automatically*, and it never said what the discount is. The case guessed 10 percent off the order. **Suppose the owner answers that members get 50 cents off every item they order.** Three lattes would be $1.50 off. This is a what-if for the case only; in your group's project, 2.8 is still open.

Don't write the change. Work out what it would do to the case as it stands. In a table with exactly these three columns, one row for every file that would have to change:

| File | What changes in it | Why it has to change |
|---|---|---|
| `Circulation.java` | the late fee goes from 25 cents a day to 50 | it works out the fee itself |

The example row is from the textbook's library system, not the case. It shows only the shape.

- Count only files under `src/main`. **Don't count the test.**
- Under the table, write the total: `Total: N files`.

| File | What changes in it | Why it has to change |
|---|---|---|
| `Order.java` | Change the discount from 10% of the subtotal to 50 cents per item. | `totalInCents()` currently calculates the 10% discount directly. |
| `Receipt.java` | Change the discount calculation and label to show 50 cents off each item. | It calculates the discount separately and displays the 10% rule. |
| `DaySales.java` | Change loyalty savings to 50 cents multiplied by the number of items. | `loyaltySavingsInCents()` currently uses 10% of the subtotal. |

`Total: 3 files`.

**Step 2: Your fix.** Change the case so that after your change, switching to that rule, or to any rule after it, changes **exactly one class under `src/main`**, and that class isn't `Order`, `Receipt` or `DaySales`.

Write the changed `Order` whole, in Java, in one code block. Put any other class you added or changed in the same block, under it. Any approach you can make work is fine. If you're not sure your idea compiles, write it anyway and put a comment on the line you're unsure of.

```java
// Order.java
public class Order {
    private final Customer customer;
    private final List<OrderLine> lines = new ArrayList<>();
    private final LoyaltyDiscount loyaltyDiscount = new LoyaltyDiscount();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void add(OrderLine line) {
        lines.add(line);
    }

    public Customer getCustomer() {
        return customer;
    }

    public int subtotalInCents() {
        int subtotal = 0;
        for (OrderLine line : lines) {
            subtotal += line.priceInCents();
        }
        return subtotal;
    }

    public int discountInCents() {
        if (customer != null && customer.isLoyaltyMember()) {
            return loyaltyDiscount.calculate(lines);
        }
        return 0;
    }

    public String discountDescription() {
        return loyaltyDiscount.description();
    }

    public int totalInCents() {
        return subtotalInCents() - discountInCents();
    }
}

// LoyaltyDiscount.java (new class)
public class LoyaltyDiscount {
    public int calculate(List<OrderLine> lines) {
        int subtotal = 0;
        for (OrderLine line : lines) {
            subtotal += line.priceInCents();
        }
        return subtotal / 10;
    }

    public String description() {
        return "10%";
    }
}

// Receipt.java
public class Receipt {
    public static String forOrder(Order order) {
        StringBuilder text = new StringBuilder();

        text.append("Subtotal: ")
            .append(dollars(order.subtotalInCents()))
            .append("\n");

        Customer customer = order.getCustomer();

        if (customer != null && customer.isLoyaltyMember()) {
            text.append("Loyalty discount (")
                .append(order.discountDescription())
                .append("): -")
                .append(dollars(order.discountInCents()))
                .append("\n");
        }

        text.append("Total: ")
            .append(dollars(order.totalInCents()));

        return text.toString();
    }

    private static String dollars(int cents) {
        return String.format("$%d.%02d",
            cents / 100, cents % 100);
    }
}

// DaySales.java
public class DaySales {
    private final List<Order> completed = new ArrayList<>();

    public void record(Order order) {
        completed.add(order);
    }

    public int revenueInCents() {
        int revenue = 0;
        for (Order order : completed) {
            revenue += order.totalInCents();
        }
        return revenue;
    }

    public int loyaltySavingsInCents() {
        int savings = 0;
        for (Order order : completed) {
            savings += order.discountInCents();
        }
        return savings;
    }
}
```

---

## 2. What I'm Not Sure About

I'm not sure whether the separate LoyaltyDiscount class will handle different discount rules without needing more information from Order. I'd test it by changing the rule to 50 cents per item and checking that three lattes get a $1.50 discount while Order.java, Receipt.java, and DaySales.java remain unchanged.

---

**Commit this file before group discussion begins.**