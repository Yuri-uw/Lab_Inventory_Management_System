### Lab Inventory Management system 
This project is designed to manage and share lab resources for college researchers to utilize the system to look up inventories and manage resource information.


### Description:
Resource sharing is an everyday activity among graduate students and laboratories, especially in experimental groups. In particular, experiments in biology and chemistry require the use of specialized equipment (e.g., chemical containers, pipettes, incubators, microscopes) and reagents that are expensive to purchase and maintain. This system is designed for graduate students to share resources at the early stages of experimental design to prevent unnecessary waste. 

The application includes a login page, an inventory page, an item page and the user information page. Here we only have the source code of the Item page and its child pages.


### Backend table and its attributes:
***Implemented in Amazon Web Services***
**Inventory**(inventoryID, entityID, quantity, expiry, ownerID)
**Items** (itemId, itemName, unit, manufacturer)
**Users** (user_id, user_name, lab, PI_name, email, phone, crypt_passwd_hash, lab_training)


### How to run this program:
https://www.jetbrains.com/help/idea/javafx.html#add-javafx-lib
###
**Before run Main, please go to src -> database -> DatabaseHandler to modift the _DB_URL,USER,PASS_ to the valid information.**

### Sample page - Items:
![image](https://user-images.githubusercontent.com/73818437/110751494-c6ac8680-81f8-11eb-8d23-0b711c4b8cfc.png)




