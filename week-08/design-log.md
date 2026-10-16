# Design Log — Week 8
### ICS 372 Object Oriented Programming | Fall
**Student:** Kaashif Khan     
**Group:** 1
**Date:** 10/15/2026
**Topic:** Implementation - wireframes, customer view, singleton 

---

## Part 1 — The Problem

*In 2-3 sentences, describe the design problem you worked on tonight in your own words. Do not copy the handout. What were you actually trying to figure out?*

The main problem we worked on this week was figuring out how different classes should get access to the objects they need in order to do what they need to do. We also had to consider whether our design would allow us to create separate objects for testing without interfering with the rest of the system.

---

## Part 2 — Your Design Decision

Originally, my group decided to use singletons for classes like OrderManager, Inventory, and Menu because we expected the coffee shop to have only one shared version of each. However, during the redesign exercise we realized it would be useful to pass objects through constructors instead of accessing them through static methods. For example, Menu.java originally used getInstance(), and BaristaInterface.java accessed Menu through that method. The redesigned approach is to have ShopSetup.java create the shop's Menu and pass it to classes that need it. PlaceOrderTest.java should create its own Menu for testing, so that changing the test menu does not affect the shop's menu or other tests.

---

## Part 3 — How You Got There

In my individual sketch for UC-2, I decided that OrderManager should receive Inventory when its constructed. I thought this made sense because OrderManager needs Inventory when completing orders, and passing it through the constructor gives it access to the correct object. During our initial group discussion, we considered using singletons instead. This seemed convenient because classes could access the same Inventory without having to pass it around. However, the test setup made me reconsider that approach. If Inventory were accessed globally, a test marking oat milk as unavailable could affect another test that needs oat milk available. Passing Inventory through the constructor would allow each test to create its own Inventory and connect it to its own OrderManager. This also makes it easier to understand which objects a class depends on.

---

## Part 4 — The Road Not Taken

I think using a Singleton for Menu could still be defended in a real coffee shop system. The shop should have one consistent menu so customers and employees see the same items and availability. If every class created its own menu, their information could become inconsistent. A Singleton would make it easier for classes to access the same menu without passing a reference through multiple constructors. The disadvantage is that tests would also share that menu unless we added another way to replace or reset it. Creating one Menu in ShopSetup and passing it to the necessary classes would provide the same consistency while making testing easier.

---

## Part 5 — What You're Uncertain About

I'm still unsure how all the constructors and dependencies should connect once we combine all four use cases. Changing OrderManager to receive Inventory might also require changes to Order and other classes that previously accessed shared objects through static methods. 

---

## Word Count: 384 Words

---