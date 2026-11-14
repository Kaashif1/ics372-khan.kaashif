# Design Log: Week 12
**Student:** Kaashif Khan 
**Group:** 1  
**Date:** 11/13/2026

**Last week's feedback:** Full marks. 

---

## Part 1: The Problem

> Tonight wasn't the activity you did. In two or three sentences, say what the problem actually was.

The problem was that our discount calculation was tied too closely to the order itself, which means changing the loyalty discount rule could require changes in multiple places. We needed a design that separates the discount calculation from the rest of the system so that future rule changes would be easier to make without affecting unrelated classes.

---

## Part 2: What We Ended With

> Describe what your group ended with, as of your group's last commit tonight, clearly enough that someone who wasn't in your room could rebuild it. Name the file it's in.

Our group ended with a DiscountRule interface and a PercentDiscountRule class. The interface defines calculateOrderTotal(Order order), while PercentDiscountRule implements the calculation using a percentage. In our current implementation the caller provides the rule to OrderManager.createOrder() which passes it into the Order constructor. When Order.calculateTotal() runs it checks the customer's loyalty membership. If the customer is a member, it delegates the calculation to the discount rule. Otherwise, it returns the subtotal without a discount. The main files involved are DiscountRule.java, PercentDiscountRule.java, Order.java, and OrderManager.java. This separates the percentage calculation from Order, although Order still checks membership, which is something we could improve later. 

---

## Part 3: One Decision That Moved or Held

> Take one decision you made alone in your sketch. Say what you chose and why it looked right at the time. Then say what happened to it tonight: what changed it, or what you heard and held against.

In my individual sketch I created a separate LoyaltyDiscount class to handle the discount calculation instead of keeping the 10% calculation inside Order. I thought this made sense because changing the discount rule would only require editing that class while Receipt and DaySales could get the discount amount directly from Order. During the group work, we moved toward using an interface called DiscountRule instead of having Order create a specific LoyaltyDiscount object. I think this is more flexible because the discount rule can be passed into the constructor, making it easier to substitute different implementations during testing. 

---

## Part 4: Argue the Other Side

> Take the option your group didn't choose and make its strongest case, fairly enough that someone who wanted it would recognize their own argument. Then say what beat it, or that nothing did.

An alternative would've been to store the discount percentage in a regular field instead of creating an interface and separate class. This would be simpler because we wouldn't need to pass another object through constructors or manage multiple implementations. If the owner confirmed that the discount would always be a fixed percentage, a field could be enough and changing the percentage would be straightforward. However, requirement 2.8 still doesn't specify exactly what the loyalty discount should be. The owner might decide to use a fixed amount per item instead of a percentage. An interface makes more sense in that situation because we can introduce a different calculation without putting another discount formula directly into Order.

---

## Part 5: What Could Break

> Name one specific thing in your design that could turn out wrong, the situation that would expose it, and what you'd need to see to find out.

One weakness in our current design is that DiscountRule.calculateOrderTotal() only returns the final order total, so it doesn't directly provide the individual discount amounts needed to display savings for each menu item. We might need to change the interface to return more information about the discount which could also affect Order and any classes that display the total. I'd test this by implementing a rule that gives different discounts to different items and checking whether the order and receipt can report both the correct total and the individual savings.     