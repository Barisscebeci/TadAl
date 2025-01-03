---

# TadAl Mobil Uygulaması

Kullanıcıların yemek ve içecek siparişlerini kolayca verebilmelerini sağlayan bir mobil uygulama.

## İçindekiler

1. [Proje Genel Bakış](#proje-genel-bakış)  
2. [Özellikler](#özellikler)  
   1. [Kullanıcı Özellikleri](#kullanıcı-özellikleri)  
3. [Ekran Görüntüleri](#ekran-görüntüleri)  
   1. [Kullanıcı Tarafı Ekranları](#kullanıcı-tarafı-ekranları)  
4. [Teknolojiler](#teknolojiler)  
5. [Kurulum](#kurulum)  
   1. [Mobil Uygulama Kurulumu](#mobil-uygulama-kurulumu)  
6. [Proje Yapısı](#proje-yapısı)  
7. [Katkıda Bulunma](#katkıda-bulunma)  
8. [Lisans](#lisans)  

---

## Proje Genel Bakış

**TadAl**, kullanıcıların yemek ve içecek siparişlerini kolayca verebilmelerini sağlayan bir mobil uygulamadır. Kullanıcı dostu arayüzüyle yemek keşfi, ürün detaylarının incelenmesi, sepet yönetimi ve favorilere ekleme gibi işlemler basit ve hızlı bir şekilde gerçekleştirilir. Firebase altyapısı sayesinde güvenilir bir veri yönetimi sunar.

---

## Özellikler

### Kullanıcı Özellikleri

- **Kayıt & Giriş**  
  - E-posta ve şifre ile kullanıcı kaydı yapma.  
  - Mevcut hesap bilgileriyle giriş yapma.

- **Anasayfa**  
  - Çeşitli kategorilerde ürünleri listeleme.  
  - Ürünleri hızlıca sepete ekleme.

- **Ürün Detayları**  
  - Ürünlerin detaylarını, fiyatını ve değerlendirme puanlarını görüntüleme.  
  - Ürün miktarını artırma veya azaltma.

- **Favoriler**  
  - Beğenilen ürünleri favorilere ekleme.  
  - Favoriler listesini görüntüleme ve yönetme.

- **Sepet Yönetimi**  
  - Sepetteki ürünlerin adetlerini değiştirme.  
  - Sepet toplam fiyatını görme ve sipariş onayı yapma.

- **Profil Yönetimi**  
  - Kullanıcı bilgilerini (isim, e-posta, adres vb.) görüntüleme.  
  - Çıkış yapma işlemini gerçekleştirme.

---

## Ekran Görüntüleri

### Kullanıcı Tarafı Ekranları

1. **Kayıt Ol Ekranı**  
   Yeni kullanıcıların hesap oluşturabildiği form ekranı.

   ![register](https://github.com/user-attachments/assets/cb098736-99f1-49fb-875a-9c5c384dbbfb)

2. **Giriş Yap Ekranı**  
   Kayıtlı kullanıcıların uygulamaya giriş yapabileceği ekran.

   ![Login](https://github.com/user-attachments/assets/7fed2eea-8186-4a6c-a634-ab84845393fe)

3. **Anasayfa**  
   Tüm ürünlerin listelendiği, ürünlerin hızlıca sepete eklenebildiği ekran.

   ![HomeScreen](https://github.com/user-attachments/assets/c2f759c2-bb3e-469a-9c63-7e0442afc526)
   
4. **Ürün Detay Ekranı**  
   -Ürünlerin detaylı bilgilerini, fiyatını ve kullanıcı değerlendirmelerini gösterir. Kullanıcı, ürün miktarını artırıp azaltabilir ve ürünü sepete ekleyebilir.

   ![UrunDetayScreen](https://github.com/user-attachments/assets/414c72a8-0bf5-40a3-a3f7-3bde4cfa65ad)

5. **Favorilerim Ekranı**  
   Kullanıcıların beğendiği ürünleri favorilere ekleyebildiği ve yönetebildiği ekran.

   ![UrunFavoriScreen](https://github.com/user-attachments/assets/a7b1155b-7a2f-443f-880f-cd63d9dc7b99)

6. **Arama Ekranı**  
   Ürünler arasında isim veya kategoriye göre arama yapılabilmesini sağlar.

   ![UrunAramaScreen](https://github.com/user-attachments/assets/e415f395-0abc-44b0-9e0e-b4869cfe3197)

7. **Sepet Ekranı**  
   Kullanıcıların sepetlerindeki ürünleri yönetebilmesini sağlar. Sepet toplamını ve ürün detaylarını gösterir.

   ![UrunSepetScreen](https://github.com/user-attachments/assets/99d7208a-db57-4dc5-a9d8-02a7517b83af)

8. **Profil Ekranı**  
   Kullanıcıların profil bilgilerini görüntüleyip düzenleyebildiği ekran.

   ![ProfileScreen](https://github.com/user-attachments/assets/df2b58e0-fc0a-47f3-8af4-cf50a73f0a6f)

---

## Teknolojiler

- **Mobil (Frontend)**
  - **Kotlin**: Uygulama geliştirme dili.
  - **Jetpack Compose**: Modern ve reaktif kullanıcı arayüzü oluşturma kütüphanesi.

- **Backend**
  - **Firebase Authentication**: Kullanıcı kimlik doğrulama.
  - **Firebase Firestore**: Veritabanı yönetimi.

---

## Kurulum

### Mobil Uygulama Kurulumu

1. **Projeyi Klonlayın**  
   ```bash
   git clone https://github.com/Barisscebeci/TadAl.git
   ```
2. **Dizine Geçin**  
   ```bash
   cd TadAl
   ```
3. **Bağımlılıkları Yükleyin**  
   - Android Studio üzerinden projeyi açın ve gerekli bağımlılıkların yüklenmesini sağlayın.
4. **Firebase Konfigürasyonu**  
   - Firebase projenizi oluşturun.  
   - `google-services.json` dosyasını indirip `app` dizinine ekleyin.
5. **Uygulamayı Çalıştırın**  
   - Android emülatör veya fiziksel cihaz üzerinde projeyi derleyip çalıştırın.

---

## Proje Yapısı

Proje içerisinde klasör yapısı şu şekilde düzenlenmiştir:

- **core**: Proje içinde temel servisleri (ör. ağ işlemleri, yapılandırmalar) barındırır.  
- **data**: Veri modelleri, veri kaynağı (remote / local) ve veri işleme (repository) sınıfları bulunur.  
- **domain**: İş mantığını yürüten `usecase` sınıflarını içerir.  
- **presentation**: Ekranlar (`screen` klasörü), bileşenler (`components`), görseller (`assets`) ve ViewModel’ler yer alır.  
- **di**: Dependency Injection (bağımlılık enjeksiyonu) ayarlarını içerir.

---

## Katkıda Bulunma

Bu projeye katkıda bulunmak isterseniz aşağıdaki adımları izleyebilirsiniz:

1. **Projeyi Fork’layın**  
   ```bash
   git fork https://github.com/Barisscebeci/TadAl.git
   ```
2. **Yeni Bir Özellik Dalı Oluşturun**  
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Değişiklikleri Commit’leyin**  
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Dalı Push Edin**  
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Pull Request Açın**  
   Ana depoya teklif gönderin ve değişikliklerin incelenmesini bekleyin.

---

## Lisans

Bu proje, açık kaynak olarak yayımlanmıştır. Daha fazla bilgi için lütfen [LICENSE](LICENSE) dosyasına bakınız.

Herhangi bir sorunuz varsa lütfen [GitHub](https://github.com/Barisscebeci/TadAl) üzerinden bizimle iletişime geçin!

---


---

Bu taslağı projenizin gereksinimlerine göre özelleştirerek kullanabilirsiniz. Başarılar!
