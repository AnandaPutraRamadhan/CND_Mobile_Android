# CND Mobile — Android starter project

Prototype aplikasi Android native berbasis Kotlin + Jetpack Compose untuk PT Citra Natural Dinamika.

## Fitur prototipe
- Beranda dan informasi layanan prapensiun/pensiun
- Identitas visual dan logo CND
- Formulir pengajuan demo (belum mengirim/menyimpan data)
- Tampilan status pengajuan demo
- Pratinjau dashboard admin (data contoh)
- Tombol WhatsApp ke 0821-1178-9110

## Membuka project
1. Ekstrak ZIP.
2. Buka folder `CND_Mobile_Android` melalui Android Studio (versi yang mendukung AGP 8.7.3).
3. Tunggu Gradle Sync selesai dan pasang Android SDK 35 jika diminta.
4. Jalankan pada emulator/perangkat Android atau pilih **Build > Build APK(s)**.

## Batasan penting
Ini adalah **prototype UI**, bukan sistem produksi dan belum menghasilkan APK terkompilasi. Login/registrasi sungguhan, database, upload dokumen, notifikasi, pengelolaan pengguna, dan sinkronisasi status belum aktif karena memerlukan backend/API, autentikasi, penyimpanan berkas, serta pengaturan hak akses admin. Tombol simpan pengajuan saat ini hanya demonstrasi. Jangan gunakan data nasabah/NIK/dokumen asli pada prototype.

Sebelum produksi, tentukan server/backend, kebijakan privasi dan persetujuan nasabah, enkripsi, retensi data, audit log, role-based access, dan proses keamanan dokumen.
