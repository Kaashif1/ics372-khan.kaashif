# Design Log — Week 4
### ICS 372 Object Oriented Programming | Fall Semester
**Student:** Kaashif Khan
**Group:** 1
**Date:** 9/17/2026 
**Topic:** Detailed Use Cases And Sequence Diagrams 

---

## Part 1 — The Problem

The main problem we worked on was figuring out whether or not our domain model supported the detailed steps in our use cases. While tracing those steps through sequence diagrams we also had to figure out which entities should handle each action and whether the model could correctly handle things such as a menu price changing after the order was already placed. 

---

## Part 2 — Your Design Decision

Our group decided that the menu price and the price associated with an already placed order need to be treated differently. An order should preserve the price that the customer agreed to when the order was placed and the menu should hold the listed price which can be modified. We also decided to keep the sequence diagram limited to the entities that actually existed in our model. These decisions are shown in `docs/week-04/group-artifact-2.md`. 

---

## Part 3 — How You Got There

In my individual sketch I focused on tracing the Customer Places Order use case through the entities in our existing model. For the detailed use case, looking at the assumption behind every step gave me an idea of where an alternative flow could exist. For example, an item being unavailable or an order failing to submit came from assumptions that the main flow depended on. 

The group discussion changed my thinking. Initially I expected our existing model to handle price, however when we traced the price change as a group, we realized that our model didn't clearly distinguish the current menu price from the price of an item already in the order. This was a problem because changing the menu price could affect what Sam appeared to pay. The requirement that an already placed order should keep it's agreed upon price made us think about seperating the current menu information from the information stored on an order. 

---

## Part 4 — The Road Not Taken

One approach we considered was keeping one item and one price that both the menu and order use. This would make the model simpler and avoid storing similar information in multiple places. However, it creates the issue that changing the menu price could change an existing order. Another group member brought up that we should preserve the price on the order when it is placed. This allows the menu price to change without changing the price that the customer already agreed to pay. This is the approach our group chose because the menu price and order price have different lifetimes. We also had different individual sequence diagrams, some with entitities that were not on our existing model so we chose a diagram that stayed more consistent.   

---

## Part 5 — What You're Uncertain About

I'm still unsure whether or not there are other places in our model where we need the same seperation between a general thing that can change and a particular instance that should stay fixed. For example, inventory changes over time while an order represents a particular transaction. If cases like this are not identified then problems can occur, such as affecting an existing order. 

---

## Word Count: 420 Words

---
