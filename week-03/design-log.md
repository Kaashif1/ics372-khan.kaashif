# Design Log — Week 3
### [ICS 372 Object Oriented Design and Implementation] | [Fall Semester]
**Student:** [Kaashif Khan]  
**Group:** [Group Number: 1]  
**Date:** [9/10/2026]  
**Topic:** [Tonight's topic — Requirements Analysis; Actors And Use Case Modelling]

---

## Part 1 — The Problem

*In 2-3 sentences, describe the design problem you worked on tonight in your own words. Do not copy the handout. What were you actually trying to figure out?*

Tonight we were trying to take a list of requirements from the client and figure out what should actually become use cases for the system. We were deciding which requirements were functional, which were constraints, and which were too conflicting or vague to design from. 

---

## Part 2 — Your Design Decision

*What did your group decide? Describe the design you landed on clearly enough that someone who wasn't in your group could understand it. If you produced a diagram, reference it here by filename.*

Our group was working on organizing the system around three actors. These actors were Customer, Barista, and Manager. We created use cases based on the outcomes each actor needs from the system instead of making every individual requirement it's own use case. For example, the Customer use case became View Menu, Build Order, Place Order, and Recieve Confirmation. 

---

## Part 3 — How You Got There

*This is the most important section. Start from your individual sketch — what did you think before your group talked? Then walk through how the group discussion changed (or didn't change) your thinking. What did the problem itself tell you about what the design needed to do? What constraints or requirements drove your decisions?*

*Avoid starting from a solution. If you found yourself thinking "we should use X pattern" or "this should be a Y" before fully understanding the problem, note that here and explain whether you went back to the problem or pushed forward anyway.*

When I was working on my own individual work, I initially treated a lot of the requirements as seperate actions and possible use cases. After I discussed with my group, I realized that use cases should describe an outcome an actor is trying to achieve rather than every individual action in the system. We ended up combininig some smaller customer actions into larger use cases. For example, adding and removing items can be simply represented as Build Order. In our discussion we also noticed that some requirements were too vague to design from. Requirement 2.7 for example says a customer should be able to fix a mistake, but it doesn't elaborate on what that means exactly and in what stage of the order it can happen. We also discussed requirement 2.8 because it has both the idea of a loyalty discount and constraints about how that loyalty discount works. Furthermore, we found questions that the requirements provided didn't have a clear answer for. 3.7 for example says that a barista can add a menu item when the manager isn't present, yet 4.1 contradicts this by saying only managers can modify the menu. Our group identified these two contradictory requirements as something that needs clarification. This helped me understand that unclear requirements sometimes need to be questioned before designing around them. 

---

## Part 4 — The Road Not Taken

*What other approaches did you consider and why did you move away from them? If your group disagreed about something, describe both positions and explain how you resolved it. If you personally favored a different approach than what the group decided, explain your reasoning — even if you were outvoted.*

Our group had a disagreement regarding requirement 2.8. I labelled 2.8 as functional because the discount has to be handelled by the system. However, another group member brought up how it could be neither because part of it describes a constraint involving the customer's loyalty discount. I can now understand the argument for classifying it as neither because the requirement mixes a business rule with system behavior rather than describing one clear function. 

---

## Part 5 — What You're Uncertain About

*What would you change about your design if you had more time or information? What are you not confident about? What might break later?*

Something I'm not too confident about is the Add Menu Item use case for the barista. 3.7 states that a barista can add a menu item when the manager isn't present. However, I'm confused on how the system would know if the manager is absent or not. On top of this, it's conflicting with the idea that menu modification is restricted to managers. This is why this use case may need to change or be removed.  

---

## Word Count: [X words]

---

*Aim for 300–500 words across Parts 2–5. Part 1 does not count toward the word count.*