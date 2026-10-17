package com.darien.myapplication.soal1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darien.myapplication.R
import java.text.NumberFormat
import java.util.Locale

enum class Screen { Listing, Detail }

enum class Vehicle(
    val title: String,
    val price: Int,
    val fuelType: String,
    val year: Int,
    val mileage: Int,
    val gearbox: String,
    val photo: Int,
    val description: String
) {
    CHERY_TIGGO(
        title = "Tiggo 9 CSH",
        price = 369000000,
        fuelType = "PHEV",
        year = 2024,
        mileage = 4500,
        gearbox = "Auto",
        photo = R.drawable.tiggo,
        description = "The Tiggo 9 CSH is a premium plug-in hybrid SUV that combines exceptional fuel efficiency with sophisticated style. Featuring advanced driver assistance systems, modern luxury interiors, and seamless EV-to-gas powertrain transitions, it delivers an elevated driving experience for daily commutes and long journeys alike."
    ),
    HONDA_CRV(
        title = "Honda CRV",
        price = 810000000,
        fuelType = "Petrol",
        year = 2013,
        mileage = 14000,
        gearbox = "Auto",
        photo = R.drawable.crv,
        description = "The Honda CRV remains a benchmark in modern compact SUVs, delivering smooth power, refined handling, and exceptional cabin comfort. Designed with spacious seating, robust safety technology, and dependable reliability, it serves as a versatile companion for both city routes and family road trips."
    ),
    TOYOTA_YARIS_CROSS(
        title = "Toyota Yaris Cross",
        price = 440000000,
        fuelType = "Hybrid",
        year = 2024,
        mileage = 32000,
        gearbox = "Auto",
        photo = R.drawable.yariscross,
        description = "The Toyota Yaris Cross brings Eco-friendly hybrid efficiency into a versatile compact crossover design. Packed with smart connectivity features, elevated ground clearance, and reactive urban agility, it provides low emissions without sacrificing adventurous weekend capability."
    ),
    DAIHATSU_XENIA(
        title = "Daihatsu Xenia",
        price = 220000000,
        fuelType = "Petrol",
        year = 2022,
        mileage = 25000,
        gearbox = "Manual",
        photo = R.drawable.xenia,
        description = "The Daihatsu Xenia is a trusted multi-purpose vehicle crafted specifically for family practicality and maximum value. Offering spacious 7-seater comfort, dependable manual control, and impressive fuel efficiency, it easily handles everyday family travel and commuter demands."
    ),
    TOYOTA_YARIS(
        title = "Toyota Yaris",
        price = 325000000,
        fuelType = "Petrol",
        year = 2021,
        mileage = 50000,
        gearbox = "Auto",
        photo = R.drawable.yaris,
        description = "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit, it offers premium features at a very accessible price point."
    ),
    HONDA_HRV(
        title = "Honda HRV",
        price = 416000000,
        fuelType = "Petrol",
        year = 2023,
        mileage = 15000,
        gearbox = "CVT",
        photo = R.drawable.hrv,
        description = "The Honda HRV merges futuristic coupe styling with flexible utility. Powered by an responsive engine paired with a continuous variable transmission, it boasts flexible Magic Seats, modern driver displays, and dynamic urban performance."
    ),
    DAIHATSU_AYLA(
        title = "Daihatsu Ayla",
        price = 135000000,
        fuelType = "Petrol",
        year = 2022,
        mileage = 20500,
        gearbox = "Auto",
        photo = R.drawable.ayla,
        description = "The Daihatsu Ayla is an ultra-compact city car optimized for nimble maneuvering and maximum fuel economy. Perfectly built for tight city navigation and effortless parking, it delivers low maintenance costs and solid daily driving reliability."
    ),
    JAECOO_J5(
        title = "Jaecoo J5",
        price = 379000000,
        fuelType = "PHEV",
        year = 2025,
        mileage = 3000,
        gearbox = "Auto",
        photo = R.drawable.j5,
        description = "The Jaecoo J5 is a cutting-edge PHEV SUV designed with bold off-road aesthetics and intelligent cockpit technology. Featuring high-capacity hybrid efficiency, rapid electric acceleration, and premium digital displays, it sets new standards for modern eco-conscious drivers."
    ),
    MG_4(
        title = "MG 4 EV",
        price = 423000000,
        fuelType = "PHEV",
        year = 2024,
        mileage = 5000,
        gearbox = "Auto",
        photo = R.drawable.mg4,
        description = "The MG 4 EV delivers striking athletic design coupled with next-generation electric mobility. Built with a balanced chassis, rapid acceleration capabilities, and advanced driver safety suites, it brings thrilling performance and futuristic style to every drive."
    ),
    INNOVA_REBORN(
        title = "Toyota Innova Reborn",
        price = 380000000,
        fuelType = "Petrol",
        year = 2021,
        mileage = 50000,
        gearbox = "Manual",
        photo = R.drawable.reborn,
        description = "The Toyota Innova Reborn stands as a premier MPV renowned for durability, plush passenger comfort, and high resale confidence. Its robust ladder-frame chassis and spacious cabin make it the quintessential long-distance cruiser for family and business needs."
    );
}

@Composable
fun Soal1View() {
    var currentScreen by rememberSaveable { mutableStateOf(Screen.Listing) }
    val favorites = remember { mutableStateMapOf<String, Boolean>() }
    val favoriteCount = favorites.values.count { it }
    var selectedVehicle by rememberSaveable { mutableStateOf(Vehicle.CHERY_TIGGO) }

    when (currentScreen) {
        Screen.Listing -> {
            Card(modifier = Modifier.fillMaxSize().background(Color(0xFFF6F4F0))) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(vertical = 30.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                    item {
                        Box(modifier = Modifier.padding(horizontal = 20.dp).width(50.dp).height(5.dp).clip(RoundedCornerShape(80.dp)).background(Color(0xFFFF5C36)))
                    }
                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    item {
                        Text("On Sale", modifier = Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold, fontSize = 40.sp)
                    }
                    item {
                        Row(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("Find your perfect ride", modifier = Modifier.fillMaxHeight(), textAlign = TextAlign.Center, color = Color.Gray)
                            Box(modifier = Modifier.height(40.dp).width(100.dp).clip(RoundedCornerShape(20.dp)).background(Color(0xFFFFEFEC)).border(2.dp, Color(0xFFFBD6CD), RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                                Text("♥\uFE0E  $favoriteCount saved", textAlign = TextAlign.Center, color = Color(0xFFFF5C36))
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        Box(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                    }
                    items(Vehicle.entries, key = { it.name }) { vehicle ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp).clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    selectedVehicle = vehicle
                                    currentScreen = Screen.Detail
                                },
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(modifier = Modifier.fillMaxSize().padding(vertical = 10.dp, horizontal = 10.dp)) {
                                Box(modifier = Modifier.width(120.dp).height(100.dp).clip(RoundedCornerShape(10.dp))) {
                                    Image(
                                        painter = painterResource(vehicle.photo), contentDescription = "Gambar mobil", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
                                    )
                                    Box(
                                        modifier = Modifier.align(Alignment.BottomStart).padding(6.dp).clip(RoundedCornerShape(8.dp))
                                            .background(if (vehicle.fuelType == "Petrol") { Color(0xFFEDE7D2) } else if (vehicle.fuelType == "Hybrid") { Color(0xFFD1E9E8) } else { Color(0xFFDAD7E5) }).padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(vehicle.fuelType.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = (if (vehicle.fuelType == "Petrol") { Color(0xFFF39C4C) } else if (vehicle.fuelType == "Hybrid") { Color(0xFF278176) } else { Color(0xFF7457B6) }))
                                    }
                                }
                                Spacer(modifier = Modifier.width(20.dp))
                                Column(modifier = Modifier.fillMaxHeight().padding(top = 6.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                        Text(vehicle.title, fontWeight = FontWeight.Bold)
                                        Text(
                                            text = if (favorites[vehicle.name] == true) { "♥\uFE0E" } else { "♡\uFE0E" },
                                            color = if (favorites[vehicle.name] == true) { Color(0xFFFF5C36) } else { Color.Gray },
                                            fontSize = 20.sp,
                                            modifier = Modifier.clickable {
                                                if (favorites[vehicle.name] == true) {
                                                    favorites[vehicle.name] = false
                                                } else {
                                                    favorites[vehicle.name] = true
                                                }
                                            }
                                        )
                                    }
                                    Box(modifier = Modifier.width(44.dp).height(30.dp).clip(RoundedCornerShape(8.dp)).border(1.dp, Color(0xFFEDECE8), RoundedCornerShape(8.dp)).background(Color(0xFFF6F4F0)), contentAlignment = Alignment.Center) {
                                        Text(vehicle.gearbox, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray, textAlign = TextAlign.Center)
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFFF5C36)))
                                            Text("${vehicle.year}", fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(start = 5.dp))
                                        }
                                        Text("${NumberFormat.getInstance(Locale.US).format(vehicle.mileage)} km", fontSize = 14.sp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Screen.Detail -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(painter = painterResource(selectedVehicle.photo), contentDescription = "Gambar Mobil", modifier = Modifier.fillMaxWidth().height(300.dp), contentScale = ContentScale.Crop)
                        Button(
                            onClick = { currentScreen = Screen.Listing },
                            modifier = Modifier.padding(top = 40.dp, start = 14.dp).size(40.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.Black, containerColor = Color.White.copy(alpha = 0.6f)),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("←", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.offset(y = -4.dp))
                            }
                        }
                        Box(
                            modifier = Modifier.padding(top = 200.dp, start = 20.dp).clip(RoundedCornerShape(8.dp))
                                .background(if (selectedVehicle.fuelType == "Petrol") { Color(0xFFEDE7D2) } else if (selectedVehicle.fuelType == "Hybrid") { Color(0xFFD1E9E8) } else { Color(0xFFDAD7E5) }).padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(selectedVehicle.fuelType.uppercase(), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = (if (selectedVehicle.fuelType == "Petrol") { Color(0xFFF39C4C) } else if (selectedVehicle.fuelType == "Hybrid") { Color(0xFF278176) } else { Color(0xFF7457B6) }))
                        }
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(top = 250.dp),
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F4F0))
                        ) {
                            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    Text(selectedVehicle.title, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                                    Text(
                                        text = if (favorites[selectedVehicle.name] == true) { "♥\uFE0E" } else { "♡\uFE0E" },
                                        color = if (favorites[selectedVehicle.name] == true) { Color(0xFFFF5C36) } else { Color.Gray },
                                        fontSize = 30.sp,
                                        modifier = Modifier.clickable {
                                            if (favorites[selectedVehicle.name] == true) {
                                                favorites[selectedVehicle.name] = false
                                            } else {
                                                favorites[selectedVehicle.name] = true
                                            }
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFFF5C36)))
                                    Text("Semarang", fontSize = 12.sp, modifier = Modifier.padding(start = 5.dp), color = Color.Gray)
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Box(modifier = Modifier.width(190.dp).height(40.dp).clip(RoundedCornerShape(8.dp)).border(1.dp, Color(0xFFFF5C36), RoundedCornerShape(8.dp)).background(Color(0xFFFFEFEC)), contentAlignment = Alignment.Center) {
                                    Text("Rp ${NumberFormat.getInstance(Locale("in", "ID")).format(selectedVehicle.price)}", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF5C36), textAlign = TextAlign.Center)
                                }
                                Spacer(modifier = Modifier.height(18.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Text("SPECIFICATIONS", fontSize = 14.sp, color = Color(0xFFAFB8C4))
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).background(Color.White).padding(horizontal = 20.dp, vertical = 18.dp)) {
                                        Text("YEAR", fontSize = 12.sp, color = Color(0xFFAFB8C4))
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text("${selectedVehicle.year}", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).background(Color.White).padding(horizontal = 20.dp, vertical = 18.dp)) {
                                        Text("MILEAGE", fontSize = 12.sp, color = Color(0xFFAFB8C4))
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text("${selectedVehicle.mileage} km", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).background(Color.White).padding(horizontal = 20.dp, vertical = 18.dp)) {
                                        Text("TRANSMISSION", fontSize = 12.sp, color = Color(0xFFAFB8C4))
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text("${selectedVehicle.gearbox}", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).background(Color.White).padding(horizontal = 20.dp, vertical = 18.dp)) {
                                        Text("FUEL TYPE", fontSize = 12.sp, color = Color(0xFFAFB8C4))
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text("${selectedVehicle.fuelType}", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Text("ABOUT THIS CAR", fontSize = 14.sp, color = Color(0xFFAFB8C4))
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                    Text(selectedVehicle.description, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFFAFB8C4))
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(modifier = Modifier.fillMaxHeight().height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)).border(1.dp, Color(0xFFFF5C36), RoundedCornerShape(8.dp)).background(Color(0xFFFFEFEC)).clickable {
                                            if (favorites[selectedVehicle.name] == true) {
                                                favorites[selectedVehicle.name] = false
                                            } else {
                                                favorites[selectedVehicle.name] = true
                                            }
                                        },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = if (favorites[selectedVehicle.name] == true) { "♥\uFE0E" } else { "♡\uFE0E" },
                                            color = if (favorites[selectedVehicle.name] == true) { Color(0xFFFF5C36) } else { Color.Gray },
                                            fontSize = 30.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Button(
                                        onClick = {},
                                        modifier = Modifier.fillMaxHeight().weight(1f),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5C36), contentColor = Color.White)
                                    ) {
                                        Text("Contact Seller", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Soal1View()
}