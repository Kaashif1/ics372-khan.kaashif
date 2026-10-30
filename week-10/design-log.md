# Design Log — Week 10
### ICS 372 Object Oriented Programming | Fall
**Student:** Kaashif Khan     
**Group:** 1
**Date:** 10/29/2026
**Topic:** Reuse — the hierarchy under requirement change, generics

**Last week's feedback:** I lost a point on Part 5 because I mentioned that other classes might change without specifying which classes or what information would help me decide. I needed to identify the specific class I'm uncertain about and explain what would resolve that uncertainty.

---

## Part 1 — The Problem

This week's problem was figuring out how classes should access collections owned by other classes without being able to change information they shouldn't control. We needed to make sure orders stayed in the correct order, completed orders could still be found for sales reports, and submitted orders couldn't be modified by other classes.

---

## Part 2 — Your Design Decision

Our group decided to keep orders in OrderManager's list and use their statuses to distinguish active orders from completed ones. This allows for completed orders to remain available for the sales report without appearing in the active queue. We also changed how certain collections are returned. Order.getItems() and OrderItem.getCustomizations() now use List.copyOf() instead of returning their original lists. This lets other classes read the collections without directly adding or removing elements. These decisions are represented in `docs/week-10/group-artifact.md`.

---    

## Part 3 — How You Got There

For my individual sketch I worked on UC-3, which involved counting how much of each menu item was sold during the day. My method in SalesReport went through the completed orders from OrderManager and then through each order's items. Initially I identified two collections being accessed. I said that SalesReport received a copy of OrderManager's orders, but received the actual items list from Order. During our group discussion, there was a disagreement about whether SalesReport received a copy or the original orders collection. We looked at OrderManager.getCompletedOrders() and saw that it creates a new ArrayList containing the completed orders. This confirmed that SalesReport receives a separate list and can't directly change OrderManager's original collection.
During the regroup we also realized that making a collection private isn't enough if its getter returns that collection directly. Because of this, we changed the item getters to return copies. We wanted each class to remain responsible for protecting the collections it owns.


---

## Part 4 — The Road Not Taken

One alternative was keeping Order.getItems() the way it originally worked by having it returning the actual list. This was more simple but it meant another class could directly remove, add, or rearrange items in an order. We moved away from this because placed orders should not be changed that way. Another possibility was using a read only view instead of a copy. For OrderManager's completed orders, we kept the approach of creating a separate filtered list because SalesReport only needs orders completed on the requested date. Our group also considered combining Inventory's two HashMaps into one but decided to keep them separate because it made food items and ingredients easier to tell apart. 

---

## Part 5 — What You're Uncertain About

I'm still unsure whether Order.getItems() returning a copy completely protects placed orders. Although another class can't change the original list, it might still be able to modify the OrderItem objects that are inside it. For example, I'd want to test whether those objects can be changed after an order is submitted. I'm also not sure whether ManagerInterface.showDailySales() can display the correct sales information using only the results from SalesReport, especially when no orders were completed that day. Testing that would help me decide whether the current methods provide everything that ManagerInterface needs.

---

## Word Count: 442 Words 

---
