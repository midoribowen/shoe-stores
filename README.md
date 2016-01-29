# Local Shoe Stores Listings App

#### A Database Management App for Local Shoe Stores, January 29th, 2016

#### By Midori Bowen

## Description

Local Shoe Stores is a Java application that allows a user to input stores and brands into a shoe stores database in order to manage lists of local offerings of stores and brands.
* On the home page, a user can add stores and brands to the database.
* Under each store's page, all the brands carried at that store are listed and a user can add a brand to that store.
* The user can also update the store's name or delete it from the database, by filling out the form to update or clicking the "Delete Store" button to delete that store.
* Under each brand page, all the stores that carry that brand are listed and a user can add a store to that brand.

#### Updating & Deleting Brands
* Under the assumption that brands do not go out of business or change their marketing frequently, the user cannot update the brand's name or delete it from the database using Local Shoe Store's front end. However, updating and deleting brands can be done manually through running the database in postgres, as described in Setup/Installation Requirements.
* To check which brand to delete or update, enter `SELECT * FROM brands;` in the command line.
* To update a brand, find the id number that corresponds with the brand and enter `UPDATE brands SET name = [NEW BRAND NAME] WHERE id = [BRAND ID NUMBER]` in the psql command line.
* To delete a brand, find the id number that corresponds with the brand and enter `DELETE FROM brands WHERE id = [BRAND ID NUMBER];` in the psql command line.

## Setup/Installation Requirements

* Install Java, PostgreSQL, and Gradle
* Clone this repository
* Navigate to your terminal and open Postgres by entering `postgres` in your command line
* Open another terminal window or tab and enter the command `psql` and create a database by entering `CREATE DATABASE shoe_stores;` in your command line. Enter `\c shoe_stores` to open this database.
* Open yet another terminal window or tab, navigate to your project directory and enter the command `psql shoe_stores < shoe_stores.sql`. This will create the tables you need to get this database running.
* In order to test, in the terminal window or tab where you are tracking your database through psql, enter `CREATE DATABASE shoe_store_test WITH TEMPLATE shoe_stores;` before each time you test. You may need to periodically enter `DROP DATABASE shoe_stores_test;` in order to clear data from your test database. After you delete this test database, you can always create it again, using the same `CREATE DATABASE` command listed above.

* To run this program in your browser, navigate to your salon directory, enter `gradle run` in your command line, and go to http://localhost:4567/ in your browser.

## Known Bugs


## Support and contact details

https://github.com/midoribowen

## Technologies Used

Java, Spark, JUnit, Fluentlenium, Velocity, PostgreSQL, Bootstrap

### License

MIT License. See LICENSE.md for details.

Copyright (c) 2016 Midori Bowen
