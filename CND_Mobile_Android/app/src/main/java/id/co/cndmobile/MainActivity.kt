package id.co.cndmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Teal = Color(0xFF087F8C)
private val TealDark = Color(0xFF075D68)
private val Purple = Color(0xFF6D3BC0)
private val Ink = Color(0xFF20313D)
private val Pale = Color(0xFFF4F8FB)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CndApp { openWhatsApp() } }
    }
    private fun openWhatsApp() {
        val url = "https://wa.me/6282111789110?text=Halo%20CND%2C%20saya%20ingin%20berkonsultasi%20mengenai%20layanan%20prapensiun%20atau%20pensiun."
        runCatching { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
    }
}

@Composable
fun CndApp(onWhatsApp: () -> Unit) {
    var tab by remember { mutableStateOf("Beranda") }
    var role by remember { mutableStateOf("Nasabah") }
    MaterialTheme(colorScheme = lightColorScheme(primary = Teal, secondary = Purple, background = Pale)) {
        Scaffold(
            containerColor = Pale,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    listOf("Beranda", "Pengajuan", "Status", "Akun").forEach { item ->
                        val icon = when (item) { "Beranda" -> Icons.Default.Home; "Pengajuan" -> Icons.Default.Description; "Status" -> Icons.Default.TrackChanges; else -> Icons.Default.Person }
                        NavigationBarItem(selected = tab == item, onClick = { tab = item }, icon = { Icon(icon, null) }, label = { Text(item) })
                    }
                }
            }
        ) { padding ->
            Column(Modifier.padding(padding).fillMaxSize()) {
                when (tab) {
                    "Beranda" -> HomeScreen(onWhatsApp) { tab = "Pengajuan" }
                    "Pengajuan" -> ApplicationScreen()
                    "Status" -> StatusScreen(role)
                    else -> AccountScreen(role, onRoleChange = { role = it }, onWhatsApp = onWhatsApp)
                }
            }
        }
    }
}

@Composable
fun TopBrand() {
    Row(Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 18.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painterResource(R.drawable.cnd_logo), contentDescription = "Logo CND", modifier = Modifier.size(58.dp))
        Spacer(Modifier.width(10.dp))
        Column { Text("Citra Natural Dinamika", fontWeight = FontWeight.Bold, color = TealDark, fontSize = 16.sp); Text("CND MOBILE", color = Purple, fontSize = 10.sp, letterSpacing = 2.sp) }
    }
}

@Composable
fun HomeScreen(onWhatsApp: () -> Unit, onApply: () -> Unit) {
    LazyColumn(contentPadding = PaddingValues(bottom = 20.dp)) {
        item { TopBrand() }
        item {
            Column(Modifier.fillMaxWidth().background(Brush.linearGradient(listOf(Color(0xFFE9F8FA), Color.White, Color(0xFFF2ECFB)))).padding(22.dp)) {
                Text("PT CITRA NATURAL DINAMIKA", color = Teal, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.5.sp)
                Spacer(Modifier.height(12.dp))
                Text("Merencanakan masa depan dengan langkah yang tepat.", fontSize = 29.sp, lineHeight = 35.sp, fontWeight = FontWeight.ExtraBold, color = Ink)
                Spacer(Modifier.height(8.dp))
                Text("Informasi dan pendampingan pengajuan pembiayaan prapensiun dan pensiun melalui jaringan mitra keuangan.", color = Ink)
                Spacer(Modifier.height(18.dp))
                Button(onClick = onApply, colors = ButtonDefaults.buttonColors(containerColor = Teal), shape = RoundedCornerShape(30.dp)) { Text("Mulai Simulasi", modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)) }
            }
        }
        item { SectionTitle("Pilih layanan") }
        item { ServiceCard("Prapensiun", "Informasi bagi pegawai aktif yang mendekati masa pensiun.", Icons.Default.Work, Purple, onApply) }
        item { ServiceCard("Pensiun", "Informasi layanan bagi calon nasabah yang telah memasuki masa pensiun.", Icons.Default.Person, Teal, onApply) }
        item { SectionTitle("Tentang CND") }
        item { InfoCard("Mitra informasi dan pendampingan pembiayaan", "CND membantu calon nasabah memahami pilihan layanan, dokumen yang diperlukan, dan alur pengajuan sesuai ketentuan lembaga mitra.") }
        item { SectionTitle("Mitra layanan") }
        item { InfoCard("Bank Capital  •  Bank Aladin Syariah  •  Bank Sahabat Sampoerna", "Produk, persyaratan, kelayakan, dan keputusan pembiayaan mengikuti kebijakan masing-masing penyedia pembiayaan.") }
        item {
            Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), colors = CardDefaults.cardColors(containerColor = Purple), shape = RoundedCornerShape(20.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Butuh informasi lebih lanjut?", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Hubungi tim CND melalui WhatsApp.", color = Color.White)
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(onClick = onWhatsApp, colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)) { Icon(Icons.Default.Chat, null); Spacer(Modifier.width(8.dp)); Text("Chat WhatsApp") }
                }
            }
        }
    }
}

@Composable fun SectionTitle(text: String) { Text(text, Modifier.padding(start = 18.dp, top = 18.dp, bottom = 8.dp), color = TealDark, fontSize = 19.sp, fontWeight = FontWeight.Bold) }
@Composable fun ServiceCard(title: String, desc: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp).clickable { onClick() }, shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) { Icon(icon, null, tint = color, modifier = Modifier.size(38.dp)); Spacer(Modifier.width(14.dp)); Column(Modifier.weight(1f)) { Text(title, fontWeight = FontWeight.Bold, color = Ink, fontSize = 17.sp); Text(desc, color = Color.Gray, fontSize = 13.sp) }; Icon(Icons.Default.ChevronRight, null, tint = color) }
    }
}
@Composable fun InfoCard(title: String, desc: String) { Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) { Column(Modifier.padding(18.dp)) { Text(title, fontWeight = FontWeight.Bold, color = Ink); Spacer(Modifier.height(6.dp)); Text(desc, color = Color.Gray, fontSize = 13.sp) } } }

@Composable
fun ApplicationScreen() {
    var name by remember { mutableStateOf("") }; var phone by remember { mutableStateOf("") }; var category by remember { mutableStateOf("Prapensiun") }; var identity by remember { mutableStateOf("") }; var note by remember { mutableStateOf("") }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp)) {
        item { Text("Formulir Pengajuan", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Ink); Text("Isi data awal untuk konsultasi dengan tim CND.", color = Color.Gray); Spacer(Modifier.height(18.dp)) }
        item { InputField("Nama lengkap", name, { name = it }) }
        item { InputField("Nomor WhatsApp", phone, { phone = it }, KeyboardType.Phone) }
        item { Text("Jenis layanan", fontWeight = FontWeight.Bold, color = Ink); Row(verticalAlignment = Alignment.CenterVertically) { listOf("Prapensiun", "Pensiun").forEach { item -> Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = category == item, onClick = { category = item }); Text(item, fontSize = 13.sp) } } } }
        item { InputField("NIK (opsional untuk konsultasi awal)", identity, { identity = it }, KeyboardType.Number) }
        item { InputField("Catatan kebutuhan (opsional)", note, { note = it }) }
        item { Spacer(Modifier.height(10.dp)); Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4DF))) { Text("Demo awal: formulir ini belum mengirim atau menyimpan data ke server. Jangan masukkan NIK atau dokumen pribadi sebelum sistem backend dan keamanan data diaktifkan.", Modifier.padding(14.dp), color = Color(0xFF76551A), fontSize = 12.sp) } }
        item { Spacer(Modifier.height(16.dp)); Button(onClick = { }, enabled = name.isNotBlank() && phone.isNotBlank(), modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Teal)) { Text("Simpan Pengajuan (Demo)") } }
    }
}
@Composable fun InputField(label: String, value: String, onChange: (String) -> Unit, keyboard: KeyboardType = KeyboardType.Text) { Column(Modifier.padding(bottom = 12.dp)) { Text(label, fontWeight = FontWeight.SemiBold, color = Ink, fontSize = 13.sp); OutlinedTextField(value, onChange, Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = keyboard), shape = RoundedCornerShape(12.dp)) } }

@Composable fun StatusScreen(role: String) {
    Column(Modifier.fillMaxSize().padding(18.dp)) {
        Text(if (role == "Admin (Demo)") "Dashboard Admin" else "Status Pengajuan", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Ink)
        Text(if (role == "Admin (Demo)") "Contoh tampilan pemantauan pengajuan." else "Pantau tahapan pengajuan Anda.", color = Color.Gray)
        Spacer(Modifier.height(18.dp))
        if (role == "Admin (Demo)") listOf("Waiting Approval", "Approved", "Reject").forEach { StatusCard(it, "Data contoh — belum terhubung ke server") }
        else { StatusCard("Belum ada pengajuan", "Pengajuan Anda akan tampil di sini setelah sistem terhubung ke server.") }
        Spacer(Modifier.height(12.dp)); Text("Tahapan umum", fontWeight = FontWeight.Bold, color = TealDark)
        listOf("Data dan dokumen diterima", "Pemeriksaan kelengkapan", "Proses persetujuan mitra", "Informasi hasil pengajuan").forEachIndexed { i, s -> Text("${i + 1}. $s", Modifier.padding(vertical = 6.dp), color = Ink) }
    }
}
@Composable fun StatusCard(title: String, desc: String) { Card(Modifier.fillMaxWidth().padding(vertical = 6.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) { Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) { Icon(Icons.Default.Assignment, null, tint = Teal); Spacer(Modifier.width(12.dp)); Column { Text(title, fontWeight = FontWeight.Bold, color = Ink); Text(desc, fontSize = 12.sp, color = Color.Gray) } } } }
@Composable fun AccountScreen(role: String, onRoleChange: (String) -> Unit, onWhatsApp: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Akun & Pengaturan", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Ink)
        Spacer(Modifier.height(18.dp)); InfoCard("Mode tampilan", "Pilih tampilan nasabah atau pratinjau dashboard admin.")
        listOf("Nasabah", "Admin (Demo)").forEach { Row(verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = role == it, onClick = { onRoleChange(it) }); Text(it, color = Ink) } }
        Spacer(Modifier.height(16.dp)); Button(onClick = onWhatsApp, colors = ButtonDefaults.buttonColors(containerColor = Teal)) { Icon(Icons.Default.Chat, null); Spacer(Modifier.width(8.dp)); Text("Hubungi CND") }
        Spacer(Modifier.height(20.dp)); Text("CND Mobile v1.0.0 • Prototype", color = Color.Gray, fontSize = 12.sp)
    }
}
