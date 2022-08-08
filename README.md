# graduation-project

*** Mikroservis mimarisine uygun şekilde dizayn edilmiş bu sistemde, java 18, RESTful, Spring Boot, RabbitMQ gibi yapılar bulunmakla beraber, PostgresSQL, MySQL, MongoDB olmak üzere 3 farklı database teknolojisi kullanılmaktadır. 

* Tablolar ve yer aldıkları konumlar:

  * users, logged_users, provinces → PostgresSQL

  * payments, purchases, travellers, travels → MySQL

  * notifications → MongoDB
  
* Toplamda 13 farklı servis bulunmaktadır:
  
  * common-service → 8100
  
  * account-service → 8101

  * admin-province-service → 8102

  * admin-travel-service → 8103

  * other-purchase-service → 8104

  * other-payment-service → 8105

  * other-travel-service → 8106

  * notification-service → 8107

  * admin-payment-service → 8108

  * admin-purchase-service → 8109

  * other-province-service → 8110

  * admin-user-service → 8111

  * other-user-service → 8112
