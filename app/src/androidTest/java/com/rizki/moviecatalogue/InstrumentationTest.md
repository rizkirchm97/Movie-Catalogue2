## Unit Testing Scenario
Melakukan pengujian tiap ViewModel dan LiveData

1. **MoviesViewModelTest**
    - Memuat **MOVIES**
        - Memanipulasi data ketika pemanggilan data **MOVIES** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan Data **MOVIES** tidak null
        - Memastikan jumlah data **MOVIES** sesuai yang diharapkan


2. **TvShowsViewModelTest**
    - Memuat **TV SHOWS**
        - Memanipulasi data ketika pemanggilan data **TV SHOWS** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan Data **TV SHOWS** tidak null
        - Memastikan jumlah data **TV SHOWS** sesuai dengan yang diharapkan 


3. **DetailMoviesViewModelTest**
    - Memuat **movies**
        - Memanipulasi data ketika pemanggilan data **MOVIES** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan data **MOVIES** tidak null
        - Memastikan data **MOVIES** sesuai dengan yang diharapkan
        - Memastikan fungsi setFav() berjalan sesuai yang diharapkan


4. **DetailTvShowsViewModelTest**
    - Memuat **TV SHOWS**
        - Memanipulasi data ketika pemanggilan data **TV SHOWS** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan data **TV SHOWS** tidak null
        - Memastikan data **TV SHOWS** sesuai dengan yang diharapkan
        - Memastikan fugnsi setFav() berjalan sesuai yang diharapkan

5. **MovieFavouriteViewModelTest**
    - Memuat **Movie yang di Favoritkan**
        - Memanipulasi data ketika pemanggilan data **FAVOURITE MOVIES** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan data **FAVOURITE MOVIES** sesuai dengan yang diharapkan

6. **TvShowFavouriteViewModelTest**
    - Memuat **Tv Show yang di Favoritkan**
        - Memanipulasi data ketika pemanggilan data **FAVOURITE TV SHOWS** dikelas **REPOSITORY**
        - Memastikan metode di kelas **REPOSITORY** terpanggil
        - Memastikan data **FAVOURITE TV SHOWS** sesuai dengan yang diharapkan


## Instrument Test Scenario
Melakukan pengujian tiap halaman dan keseluruhan Aplikasi

1. **Menampilkan data Movies**
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Memastikan **rv_movies** dalam keadaan tampil
    - Gulir **rv_movies** ke posisi data terakhir


2. **Menampilkan data detail Movies**
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Memberi tindakan klik data pertama di **rv_movies**
    - Memastikan **TextView** untuk **tv_detail_title** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_rd** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_genre** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_tt** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_quote** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_txt_overview** tampil sesuai dengan yang diharapkan
    - Memastikan **ImageView** untuk **img_poster** dalam keadaan tampil

3. **Menampilkan data Movies Favourite**
    - Meberikan tindak Klik pada Navigasi atau Fragment Favourite
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Memastikan data pada **rv_fav_movies** sudah tampil sesuai yang diharapkan
    - Gulir **rv_fav_movies** hingga posisi terakhir
    
4. **Melakukan Aksi Klik Favourite dan menampilkannya pada Movie Favourite dan melakukan UnFavourite**
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Memastikan data pada **rv_movies** telah tampil
    - Melakukan aksi Klik data pertama pada **rv_movies** 
    - Memastikan data pada **tv_detail_title**, **tv_detail_rd**, dan **tv_detail_genre** telah tampil
    - Melakukan aksi Klik pada **action_fav**
    - Melakukan Aksi kembali pada halaman sebelumnya dengan menggunakan method **pressBack()**
    - Kembali lagi ke Fragment **nav_fav**
    - Melakukan aksi Klik data pertama pada **rv_fav_movies**
    - Memastikan data pada **tv_detail_title**, **tv_detail_rd**, **tv_detail_genre**, **tv_detail_tt**,
      **tv_detail_quote**, **tv_detail_overview**, dan **img_poster** telah tampil
    - Melakukan Aksi Klik pada **action_fav** untuk melakukan UnFavourite
    

5. **Menampilkan data TV SHOWS**
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Klik navigasi dengan Id **nav_tvShow**
    - Memastikan **rv_tv_shows** dalam keadaan tampil
    - Gulir **rv_tv_shows** ke posisi terakhir
    

6. **Menampilkan data detail TV SHOWS**
    - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
    - Klik Navigasi dengan Id **nav_tvShow**
    - Memberi tindakan klik data pertama di **rv_tv_shows**
    - Memastikan **TextView** untuk **tv_detail_title_tvShows** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_network_tvShows** tampil sesuai dengan yang diharapkan 
    - Memastikan **TextView** untuk **tv_detail_genre_tvShows** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_tt_tvShows** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_quote** tampil sesuai dengan yang diharapkan
    - Memastikan **TextView** untuk **tv_detail_overview** tampil sesuai dengan yang diharapkan
    - Memastikan **ImageView** untuk **img_poster_tvShows** dalam keadaan tampil           

7. **Menampilkan data Tv Show Favourite**
       - Meberikan tindak Klik pada Navigasi atau Fragment Favourite
       - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
       - Melakukan aksi Klik pada TabLayout **TV SHOWS**
       - Memastikan data pada **rv_fav_tv_shows** sudah tampil sesuai yang diharapkan
       - Gulir **rv_fav_tv_shows** hingga posisi terakhir
       
8. **Melakukan Aksi Klik Favourite dan menampilkannya pada Tv Show Favourite dan melakukan UnFavourite**
       - Memastikan data selesai dimuat terlebih dahulu dengan menggunakan **ESPRESSO IDLINGRESOURCE**
       - Klik Navigasi dengan Id **nav_tvShow**
       - Memastikan data pada **rv_tv_shows** telah tampil
       - Melakukan aksi Klik data pertama pada **rv_tv_show** 
       - Memastikan data pada **tv_detail_title_tvShows**, **tv_detail_network_tvShows**, dan **tv_detail_genre_tvShows** telah tampil
       - Melakukan aksi Klik pada **action_fav**
       - Melakukan Aksi kembali pada halaman sebelumnya dengan menggunakan method **pressBack()**
       - Kembali lagi ke Fragment **nav_fav**
       - Melakukan aksi Klik pada TabLayout **TV SHOWS**
       - Melakukan aksi Klik data pertama pada **rv_fav_tv_shows**
       - Memastikan data pada **tv_detail_title_tvShows**, **tv_detail_network_tvShows**, **tv_detail_genre_tvShows**, **tv_detail_tt_tvShows**,
         **tv_detail_quote**, **tv_detail_overview**, dan **img_poster_tvShows** telah tampil
       - Melakukan Aksi Klik pada **action_fav** untuk melakukan UnFavourite