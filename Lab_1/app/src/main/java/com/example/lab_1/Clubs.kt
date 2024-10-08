package com.example.lab_1

data class Clubs(var name: String, var img: Int, var details: String) {
    object RecyclerRepo {
        fun getClubs() = listOf<Clubs>(
            Clubs("Al Ahly", R.drawable.ahly, "Best Egyptian Club"),
            Clubs("Real Madrid", R.drawable.madrid, "Best Club Ever"),
            Clubs("Man United", R.drawable.man_united, "Best Club in England"),
            Clubs("Man City", R.drawable.man_city, "British Club"),
            Clubs("Zamalek", R.drawable.zamalek, "Normal Egyptian Club"),
            Clubs("Al Nasr", R.drawable.alnassr, "Saudi Club"),
            Clubs("Barchelona", R.drawable.barcelona, "Normal Spanish Club"),
            Clubs("Bayern", R.drawable.bayern, "Best German Club"),
            Clubs("Chelsea", R.drawable.chelsea, "English Club"),
            Clubs("AC Milan", R.drawable.milan, "Best Italian Club")
        )
    }
}