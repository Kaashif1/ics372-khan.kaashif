# Design Log: Week 11
**Student:** Kaashif Khan  
**Group:** 1  
**Date:** 11/6/2026

**Last week's feedback:** Full marks.

---

## Part 1: The Problem

> Tonight wasn't the activity you did. In two or three sentences, say what the problem actually was.

The problem was figuring out how different parts of our system should communicate without having to depend too much on each other. We needed a way for screens to receive notifications about completed orders and low inventory without having to constantly change the classes responsible for those operations.

---

## Part 2: What We Ended With

> Describe what your group ended with, as of your group's last commit tonight, clearly enough that someone who wasn't in your room could rebuild it. Name the file it's in.

Our group decided to use the Observer pattern to handle notifications, particularly for low inventory. We planned to have Inventory notify registered listeners whenever an ingredient's quantity falls below a certain threshold. We also decided to store the low stock threshold in Inventory instead of Ingredient. However, we didn't finish implementing the Observer pattern during class. Our group artifact describes the approach we chose and what still needs to be completed, but the notification code is not finished yet. Our decisions are recorded in `docs/week-11/group-artifact.md`.

---

## Part 3: One Decision That Moved or Held

> Take one decision you made alone in your sketch. Say what you chose and why it looked right at the time. Then say what happened to it tonight: what changed it, or what you heard and held against.

In my individual sketch, I chose to let OrderDesk register screens using a list of Consumer<List<Order>> callbacks. Whenever an order was placed or completed, OrderDesk would send the updated queue to each registered screen. I thought this was a good approach because adding another screen wouldn't require changing OrderDesk's constructor. During our group work, we discussed the Observer pattern more generally, especially for Inventory notifications. This followed a similar idea of notifying listeners instead of directly calling specific screens. The main difference was that our group focused on low stock events rather than sending copies of the entire order queue. We didn't finish the implementation, so my original approach wasn't tested against the group's design.

---

## Part 4: Argue the Other Side

> Take the option your group didn't choose and make its strongest case, fairly enough that someone who wanted it would recognize their own argument. Then say what beat it, or that nothing did.

One alternative we discussed was storing the low stock threshold in Ingredient instead of Inventory. This could make sense because different ingredients might need different warning levels. For example, an ingredient used a lot might need a higher threshold than one that is used less. Our group chose Inventory because it already keeps track of ingredient quantities. So handling the warning threshold there seemed reasonable. However, I think the alternative still has merit, especially if we eventually need separate thresholds for different ingredients.

---

## Part 5: What Could Break

> Name one specific thing in your design that could turn out wrong, the situation that would expose it, and what you'd need to see to find out.

One thing I'm uncertain about is whether Inventory can handle low stock warnings correctly if different ingredients need different thresholds. For example, if oat milk needs a warning at 20 units but coffee only needs one at 10 units, a single shared threshold could end up producing incorrect warnings. To find out I'd need to see how our group implements the threshold in Inventory and test it with two ingredients that have different warning levels. If the current approach can't handle that, we might need to store a separate threshold for each ingredient or reconsider placing the threshold in Ingredient. 