Write a small program which provides functionalities for administrating invoices. 
Mainly we want to be able to generate, order, filter and edit a list of invoices. The following Business Objects are involved: Company, representing a commercial entity. It has the following properties: name phone number Product, representing a commercial product. It has the following properties: product number (unique identifier) name price Invoice, representing a fiscal proof of a commercial intend.
It has the following properties: invoice number (unique identifier) seller products total due date pay date (optional)

Subtask 1: Generate Companies Define a set of 8 strings that will be used later to represent first names of the companies. E.g. "Romanian", "European",  "Electricity", "Incorporated" ... "Food", Generate a set of 24 with the following rules: Companies company name should be composed from 2 or 3 strings from the pool of names (use space as separator). at least 3 companies should have 3 strings in their name: E.g. "Romanian Food Incorporated" all strings appearing in a company name should be different. 
no full company name should be repeated. the phone number can be a random number of 14 digits. 
Subtask 2:  Generate Products Generate a set of 48  with the following rules: Products product number is unique. name is composed of 5 random alphabetical characters (please use the English alphabet). 
each name should be unique. price is a random number between 0.1 and 999.9
Subtask 3: Generate Invoices Generate a set of 50  with the following rules: Invoices all companies generated at Subtask no. 1 must be present as seller in at least one invoice. each invoice must contain at least one product generated in but no more than 3 products. Subtask no. 2 total represents the sum of prices for the products involved in the invoice. due date is any date in the next 5 days. Time of day is optional. pay date is any date in the previous 5 days (including the current day).  Time of day is optional. pay date is optional. If pay date is missing, the invoice is not paid yet ("unpaid"). future dates are not allowed. If you choose to ignore time, current day should not be considered as future date. 
at least 10% of the generated invoices must be "unpaid".
Subtask 4: Equality vs. Identity If two invoices have equal properties, except for invoice number, these are considered . equal Add an extra invoice which is  with an existing one. You can do this directly via code after code for Subtask 3 is completed. 
UI is optional. equal Accept this new invoice but mark it as a "duplicate". 
Subtask 5: Ordered invoices Output the invoices in an orderly manner: Pending invoices should appear first, ordered by due date (closest date first). Show how many days are left until the payment is due. Pending invoices are followed by the paid invoices, ordered by paid date (latest date first). Show date when invoice was paid. If any duplicated invoice are present, add a marker indicating that an invoice is a duplicate. Properties needed in the output: invoice number seller dates products (names only) total. 
duplication flag (if the invoice is a duplicated one). 
Subtask 6: Text Search Filter the invoice list based on a give text (quick text search): Text must be received from the user of the program. Text should contain at least 3 characters. List of results should be order as such: Invoices with the first word in seller name matching with the given text. Invoices with the second word in seller name matching with the given text. Invoices with any content of seller name matching with the given text. Invoices with any product name matching with the given text. List of results should contain 10 items or less. Properties needed in the output: seller name dates product (names only). 
try to highlight the text match. This depends on the type of UI you want to use. (Optional)
Subtask 7: Editing Allow the user of the program to "pay" a specific invoice. Depending on the UI you write, let the user specify an invoice he/she wants to mark as paid. This will update the "pay date" property of the invoice with the current day. 
Remember that pay date is an optional property. 
Subtask 9: Complete User Flow Note that you can complete the previous subtasks without writing any UI. Any alternative channels for input and output are accepted. if the user chooses to generate new data please jump to step 2. if the user chooses to use persisted data, please jump to . step 6 Let the user choose to input the set of Company names or use the predefined ones (from Subtask no. ). Let the user input the number of Companies that will be generated (used at Subtask no. 1). Let the user input the number of Products that will be generated (used at Subtask no. 2). Let the user input the number of Invoices that will be generated (used at Subtask no. 3). Let the user choose one of the following: to see the ordered invoice list (from Subtask no. 5). to duplicate an invoice (from Subtask no. 4). search the list by giving a text (from Subtask no. 6). mark an invoice as paid (from Subtask no. 7). 
persist the current data (from Subtask no. 8). After each operation the user should be able to repeat the choice at step 7.
