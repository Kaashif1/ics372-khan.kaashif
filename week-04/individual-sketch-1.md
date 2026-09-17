# Individual Sketch — Week 4 Round 1
**Student:** Kaashif Khan
**Date:** 9/17/2026


---

## 1. Tonight's Prompt

01 - Individual Sketch

Customer Places Order

Working alone, on yours:

1. Write the main flow, using the call-and-response format from the template. Precondition, numbered steps alternating between actor and system, postcondition.

**Precondition:** Customer can access the system to order and menu is available.

1. **Customer:** Selects item from the menu
2. **System:** Displays the selected item and adds it to the current order
3. **Customer:** Confirms the items and chooses to place the order
4. **System:** Displays the order total and asks the customer to confirm
5. **Customer:** Confirms the order
6. **System:** Creates the order and marks it as submitted
7. **System:** Makes the submitted order available for the barista to prepare

**Postcondition:** The order has been submitted and is available for the barista to prepare.

2. Then go back through it and annotate every step with what it assumes. One phrase per step in the margin. Step 2 assumes the item is available. Step 5 assumes the order can still be changed. Do this for every step, even the ones that seem to assume nothing.

1. **Assumption:** The customer is able to select a menu item
2. **Assumption:** The selected item is available
3. **Assumption:** The customer is finished choosing items
4. **Assumption:** The system is able to calculate the total for the order
5. **Assumption:** The order can still be modified or confirmed 
6. **Assumption:** The order is valid and can be submitted
7. **Assumption:** The system successfully saved the order

3. Turn two of those assumptions into alternative flows. Keyed to the step where they branch, with what happens instead and where it rejoins — or that it ends the use case.

**Alternative Flow 2a — Item is unavailable**

At Step 2, if the selected item is unavailable:

1. System warns the customer that the item is unavailable
2. The customer selects a different item
3. Back into the main flow at Step 2

**Alternative Flow 6a — Order cannot be submitted**

At Step 6, if the order cannot be submitted:

1. The system doesn't create the order
2. The system tells the customer that the order could not be submitted
3. The customer returns to the order and corrects the problem
4. Back into the main flow at step 3

---

## 2. What I'm Not Sure About

I'm not sure if I identified the best assumptions for each step that I had. 

---

**Commit this file before group discussion begins.**