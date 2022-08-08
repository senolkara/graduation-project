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

* Endpoints:

- account-service
    - `/api/v1/account`
        - `@PostMapping("/login")`
        - `@GetMapping("logged-user-control/{userId}")`
- admin-province-service
    - `/api/v1/admin/{loggedUserId}/province`
        - `@GetMapping`
        - `@PostMapping`
        - `@GetMapping("/{id}")`
        - `@PatchMapping("/{id}")`
        - `@DeleteMapping("/{id}")`
- admin-travel-service
    - `/api/v1/admin/{loggedUserId}/travel`
        - `@GetMapping`
        - `@PostMapping`
        - `@GetMapping("/{id}")`
        - `@PatchMapping("/{id}")`
        - `@DeleteMapping("/{id}")`
        - `@GetMapping("/search")`
- other-purchase-service
    - `/api/v1/other/purchase`
        - `@GetMapping("/logged/{loggedUserId}")`
        - `@PostMapping("/logged/{loggedUserId}")`
        - `@GetMapping("/{id}/logged/{loggedUserId}")`
- other-payment-service
    - `/api/v1/other/payment`
        - `@GetMapping("/{id}/logged/{loggedUserId}")`
        - `@PostMapping("/logged/{loggedUserId}")`
- other-travel-service
    - `/api/v1/other/travel`
        - `@GetMapping("/logged/{loggedUserId}")`
        - `@GetMapping("/{id}/logged/{loggedUserId}")`
        - `@PatchMapping("/{id}/logged/{loggedUserId}")`
        - `@GetMapping("/search/logged/{loggedUserId}")`
- admin-payment-service
    - `/api/v1/admin/{loggedUserId}/payment`
        - `@GetMapping("/{id}")`
        - `@DeleteMapping("/{id}")`
- admin-purchase-service
    - `/api/v1/admin/{loggedUserId}/purchase`
        - `@GetMapping`
        - `@GetMapping("/{id}")`
        - `@DeleteMapping("/{id}")`
- other-province-service
    - `/api/v1/other/province`
        - `@GetMapping("/{id}/logged/{loggedUserId}")`
- admin-user-service
    - `/api/v1/admin/{loggedUserId}/user`
        - `@GetMapping`
        - `@GetMapping("/profile")`
        - `@PostMapping`
        - `@GetMapping("/profile/{id}")`
        - `@PatchMapping("/{id}")`
        - `@DeleteMapping("/{id}")`

* common-service adındaki servisimiz proje içerisindeki ortak DTO - Entity dönüşümlerimini sağladığımız, kullanılan ortak Enum değerlerin olduğu ve yine Request’lerimizi ortak bir noktadan yönetebildiğimiz bir servistir. Diğer servislerimizde common-service i kullanmak istediğimizde pom.xml içerisine dependency olarak eklenmiştir ve böylelikle Dto’larımızda, Request’lerimizde, Enum’larımızda herhangi bir değişikliğe gitmek istediğimizde tek tek servis servis gezip bunu yapmak yerine tek bir noktadan common-service üzerinden bunu gerçekleştirebiliyoruz.

* Bazı yerlerde entitylerde ilişkiler kurulmuştur fakat mikroservis mimarisinin doğası gereği servislerin ayrı ayrı yapılandırılması gerektiğinden dolayı birbiriyle ilişkide olması gereken modellerimiz, entitylerimiz maalesef bu yapıda o ilişkiyi sağlamıyor. Aslında çözüm entitylerin olması gereken yerlerde tek tek tekrar tekrar yazılması gibi anlıyorum. Fakat bir nevi anlamsız ve mantıksız gelebileceğini düşünerek ve araştırmalarım da sonucuna dayanarak bu külfetten kurtulmak adına örneğin User user gibi bir kolonum olacakken Long userId şeklinde bir yöntem izledim. Böylelikle entitylerin oluşması aşamasındaki o hatalar zincirden biraz da olsa kurtulmuş oluyoruz. Yine de tek bir yerde ilişki olacaksa bir tablo ile diğer tablo arasında iki tarafa da aynı modelleri, entityleri ekleyebiliriz.

* Genel anlamda abstract factory design pattern tasarım kalıbı benimsenmiştir. Proje incelendiğinde birçok yerde görülebilir. Örneğin users tablosu varsayılan olarak PostgresSQL, travels tablosu ise MySQL üzerinde bulunmaktadır. İlerleyen süreçlerde artık users tablosuyla MySQL’de, travels tablosuyla PostgresSQL’de çalışalım demek istediğimizde rahatlıkla bunu gerçekleştirebiliriz. Böylelikle Dependency Injection anlamında loose coupling sağlamış oluyoruz. Aynı interface den türeyen birden fazla servisin olduğu durumlarda @Primary annotation kullanılarakta çözüme kavuşmuş oluyoruz.

* Postman Örnek Veriler:

  * localhost:8101/api/v1/account/login → POST
  * {
        "email": "abc@hotmail.com",
        "password": "12345678"
    }
    
  * Yukarıdaki bilgiyle admin kullanıcı girişi yapmış oluyoruz.

  * {
        "email": "abcd@hotmail.com",
        "password": "12345678"
    }
    
  * Yukarıdaki bilgiyle bireysel kullanıcı girişi yapmış oluyoruz.

  * {
        "email": "abcde@hotmail.com",
        "password": "12345678"
    }  
  * Yukarıdaki bilgiyle kurumsal kullanıcı girişi yapmış oluyoruz.

  * localhost:8103/api/v1/admin/1/travel → POST
  * {
         "provinceFrom": 9,
         "provinceWhere": 7,
         "capacity": 189,
         "amount": 1780.00,
         "date": "2022-08-12",
         "vehicleType": "PLANE",
         "statusType": "ACTIVE"
     }
   
   * Yukarıdaki bilgiyle admin girişi yapan kişi bir seyahat(sefer) eklemiş oluyor. 

   * localhost:8104/api/v1/other/purchase/logged/2 → GET

   * Giriş yapmış kullanıcı satın almış olduğu biletleri listeler.

   * localhost:8104/api/v1/other/purchase/logged/2 → POST 

   * {
          "purchaseRequests": [
              {
                  "travelId": 2,
                  "travellerRequest": {
                      "firstName": "mehmet",
                      "lastName": "kale",
                      "email": "abc20@hotmail.com",
                      "phone": "05056878645",
                      "genderType": "MALE"
                  }
              },
              {
                  "travelId": 2,
                  "travellerRequest": {
                      "firstName": "ali",
                      "lastName": "kale",
                      "email": "abc21@hotmail.com",
                      "phone": "05056878647",
                      "genderType": "MALE"
                  }
              },
              {
                  "travelId": 2,
                  "travellerRequest": {
                      "firstName": "ayşe",
                      "lastName": "kale",
                      "email": "abc22@hotmail.com",
                      "phone": "050568786452",
                      "genderType": "FEMALE"
                  }
              }
          ],
          "paymentRequest": {
              "nameOnTheCard": "ahmet kale",
              "cardNo": "1234567899874523215225",
              "validDate": "2022-08-30",
              "cvv": "875",
              "paymentType": "CREDIT_CARD",
              "cardInfoSaveOrNot": false
          }
      }
      
* İstekte gönderilen travel id li sefer için 2’si erkek 1’kadın olmak üzere 3 kişi adına bilet satın alınıyor. Burada eğer son kişi de erkek olsaydı en fazla 2 erkek yolcu alınabilir şeklinde bir uyarı alınacaktı. 
