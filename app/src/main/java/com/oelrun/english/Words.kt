 package com.oelrun.english


class Words(unitId: Int) {
    data class Item (
            val source: Int,
            val sound: Int,
            val eng: String,
            val rus: String)

    var data: MutableList<Item>

    private val colors: MutableList<Item> = mutableListOf(
            Item(R.drawable.black, R.raw.black, "black", "чёрный"),
            Item(R.drawable.blue, R.raw.blue,"blue", "синий"),
            Item(R.drawable.green, R.raw.green,"green", "зелёный"),
            Item(R.drawable.brown, R.raw.brown,"brown", "коричневый"),
            Item(R.drawable.orange, R.raw.orange,"orange", "оранжевый"),
            Item(R.drawable.pink, R.raw.pink,"pink", "розовый"),
            Item(R.drawable.purple, R.raw.purple,"purple", "фиолетовый"),
            Item(R.drawable.red, R.raw.red,"red", "красный"),
            Item(R.drawable.white, R.raw.white,"white", "белый"),
            Item(R.drawable.yellow, R.raw.yellow, "yellow", "желтый")
    )
    private val things: MutableList<Item> = mutableListOf(
            Item(R.drawable.pencil, R.raw.pencil,"pencil", "карандаш"),
            Item(R.drawable.pen, R.raw.pen,"pen", "ручка"),
            Item(R.drawable.rubber,R.raw.rubber, "rubber", "ластик"),
            Item(R.drawable.brush, R.raw.brush,"brush", "кисточка"),
            Item(R.drawable.clip, R.raw.clip,"clip", "скрепка"),
            Item(R.drawable.globe, R.raw.globe,"globe", "глобус"),
            Item(R.drawable.scissors, R.raw.scissors,"scissors", "ножницы"),
            Item(R.drawable.glue, R.raw.glue,"glue", "клей"),
            Item(R.drawable.keys, R.raw.keys,"keys", "ключи"),
            Item(R.drawable.mail, R.raw.mail,"mail", "почта")
    )
    private val food: MutableList<Item> = mutableListOf(
            Item(R.drawable.salad, R.raw.salad,"salad", "салат"),
            Item(R.drawable.bread, R.raw.bread,"bread", "хлеб"),
            Item(R.drawable.rice, R.raw.rice,"rice", "рис"),
            Item(R.drawable.meat, R.raw.meat,"meat", "мясо"),
            Item(R.drawable.fish, R.raw.fish,"fish", "рыба"),
            Item(R.drawable.chicken, R.raw.chicken,"chicken", "курица"),
            Item(R.drawable.cheese, R.raw.cheese,"cheese", "сыр"),
            Item(R.drawable.pasta, R.raw.pasta,"pasta", "макароны"),
            Item(R.drawable.pizza, R.raw.pizza,"pizza", "пицца"),
            Item(R.drawable.sandwich, R.raw.sandwich,"sandwich", "бутерброд")
    )
    private val capOfTea: MutableList<Item> = mutableListOf(
            Item(R.drawable.cookies, R.raw.cookies,"cookies", "печеньки"),
            Item(R.drawable.cake, R.raw.cake,"cake", "торт"),
            Item(R.drawable.milk, R.raw.milk,"milk", "молоко"),
            Item(R.drawable.juice, R.raw.juice,"juice", "сок"),
            Item(R.drawable.water, R.raw.water,"water", "вода"),
            Item(R.drawable.tea, R.raw.tea,"tea", "чай"),
            Item(R.drawable.sweets, R.raw.sweets,"sweets", "конфеты"),
            Item(R.drawable.chocolate, R.raw.chocolate,"chocolate", "шоколад"),
            Item(R.drawable.milkshake, R.raw.milkshake,"milkshake", "молочный коктель"),
            Item(R.drawable.yogurt, R.raw.yogurt,"yogurt", "йогурт")
    )
    private val outside: MutableList<Item> = mutableListOf(
            Item(R.drawable.raining, R.raw.raining,"raining", "дождь"),
            Item(R.drawable.snowing, R.raw.snowing,"snowing", "снег"),
            Item(R.drawable.windy, R.raw.windy,"windy", "ветрено"),
            Item(R.drawable.cold, R.raw.cold,"cold", "холодно"),
            Item(R.drawable.hot, R.raw.hot,"hot", "жарко"),
            Item(R.drawable.sunny, R.raw.sunny,"sunny", "солнечно"),
            Item(R.drawable.spring, R.raw.spring,"spring", "весна"),
            Item(R.drawable.summer, R.raw.summer,"summer", "лето"),
            Item(R.drawable.autumn, R.raw.autumn,"autumn", "осень"),
            Item(R.drawable.winter, R.raw.winter,"winter", "зима")
    )
    private val farm: MutableList<Item> = mutableListOf(
            Item(R.drawable.cow, R.raw.cow,"cow", "корова"),
            Item(R.drawable.goat, R.raw.goat,"goat", "коза"),
            Item(R.drawable.horse, R.raw.horse,"horse", "лошадь"),
            Item(R.drawable.sheep, R.raw.sheep,"sheep", "овечка"),
            Item(R.drawable.rabbit, R.raw.rabbit,"rabbit", "кролик"),
            Item(R.drawable.mouse, R.raw.mouse,"mouse", "мышь"),
            Item(R.drawable.duck, R.raw.duck,"duck", "утка"),
            Item(R.drawable.pig, R.raw.pig,"pig", "свинья"),
            Item(R.drawable.bird, R.raw.bird,"bird", "птица"),
            Item(R.drawable.lizard, R.raw.lizard,"lizard", "ящерица")
    )
    private val zoo: MutableList<Item> = mutableListOf(
            Item(R.drawable.elephant, R.raw.elephant,"elephant", "слон"),
            Item(R.drawable.giraffe, R.raw.giraffe,"giraffe", "жираф"),
            Item(R.drawable.monkey, R.raw.monkey,"monkey", "обезьяна"),
            Item(R.drawable.tiger, R.raw.tiger,"tiger", "тигр"),
            Item(R.drawable.bear, R.raw.bear,"bear", "медведь"),
            Item(R.drawable.crocodile, R.raw.crocodile,"crocodile", "крокодил"),
            Item(R.drawable.zebra, R.raw.zebra,"zebra", "зебра"),
            Item(R.drawable.penguin, R.raw.penguin,"penguin", "пингвин"),
            Item(R.drawable.snake, R.raw.snake,"snake", "змея"),
            Item(R.drawable.turtle, R.raw.turtle,"turtle", "черепаха")
    )
    private val canI: MutableList<Item> = mutableListOf(
            Item(R.drawable.help, R.raw.help,"help", "помогать"),
            Item(R.drawable.walk, R.raw.walk,"walk", "идти пешком"),
            Item(R.drawable.talk, R.raw.talk,"talk", "разговаривать"),
            Item(R.drawable.climb, R.raw.climb,"climb", "взбираться"),
            Item(R.drawable.draw, R.raw.draw,"draw", "рисовать"),
            Item(R.drawable.sing, R.raw.sing,"sing", "петь"),
            Item(R.drawable.dance, R.raw.dance,"dance", "танцевать"),
            Item(R.drawable.read, R.raw.read,"read", "читать"),
            Item(R.drawable.write, R.raw.write,"write", "писать"),
            Item(R.drawable.cook, R.raw.cook,"cook", "готовить")
    )
    private val clothes: MutableList<Item> = mutableListOf(
            Item(R.drawable.skirt, R.raw.skirt,"skirt", "юбка"),
            Item(R.drawable.scarf, R.raw.scarf,"scarf", "шарф"),
            Item(R.drawable.dress, R.raw.dress,"dress", "платья"),
            Item(R.drawable.tshirt, R.raw.tshirt,"t-shirt", "футболка"),
            Item(R.drawable.trousers, R.raw.trousers,"trousers", "штаны"),
            Item(R.drawable.shoes, R.raw.shoes,"shoes", "обувь"),
            Item(R.drawable.coat, R.raw.coat,"coat", "куртка"),
            Item(R.drawable.shorts, R.raw.shorts,"shorts", "шорты"),
            Item(R.drawable.socks, R.raw.socks,"socks", "носки"),
            Item(R.drawable.hat, R.raw.hat,"hat", "шапка")
    )
    private val fruits: MutableList<Item> = mutableListOf(
            Item(R.drawable.apple, R.raw.apple,"apple", "яблоко"),
            Item(R.drawable.banana, R.raw.banana,"banana", "банан"),
            Item(R.drawable.orangefruit, R.raw.orangefruit,"orange", "апельсин"),
            Item(R.drawable.cherry, R.raw.cherry,"cherry", "вишня"),
            Item(R.drawable.pear, R.raw.pear,"pear", "груша"),
            Item(R.drawable.plum, R.raw.plum,"plum", "слива"),
            Item(R.drawable.fig, R.raw.fig,"fig", "инжир"),
            Item(R.drawable.peach, R.raw.peach,"peach", "персик"),
            Item(R.drawable.grapes, R.raw.grapes,"grapes", "виноград"),
            Item(R.drawable.pineapple, R.raw.pineapple,"pineapple", "ананас")
    )
    private val veggies: MutableList<Item> = mutableListOf(
            Item(R.drawable.tomato, R.raw.tomato,"tomato", "помидор"),
            Item(R.drawable.cucumber, R.raw.cucumber,"cucumber", "огурец"),
            Item(R.drawable.carrot, R.raw.carrot,"carrot", "морковь"),
            Item(R.drawable.cabbage, R.raw.cabbage,"cabbage", "капуста"),
            Item(R.drawable.corn, R.raw.corn,"corn", "кукуруза"),
            Item(R.drawable.mushroom, R.raw.mushroom,"mushroom", "гриб"),
            Item(R.drawable.potato, R.raw.potato,"potato", "картошка"),
            Item(R.drawable.onion, R.raw.onion,"onion", "лук"),
            Item(R.drawable.pepper, R.raw.pepper,"pepper", "перец"),
            Item(R.drawable.pumpkin, R.raw.pumpkin,"pumpkin", "тыква")
    )

    init {
        data = when(unitId) {
            1 -> colors
            2 -> things
            3 -> food
            4 -> capOfTea
            5 -> outside
            6 -> farm
            7 -> zoo
            8 -> canI
            9 -> clothes
            10 -> fruits
            else -> veggies
        }
    }
}

class HelpData(unitId: Int) {
    private val unitTextList = listOf<String>(
            "Apple is red..\n Orange is orange?",
            "Give me some clips, please",
            "I like fish, do you?",
            "A mad tea-party",
            "It's raining outside, isn't it?",
            "What’s your favourite animal?",
            "Are you afraid of snakes?",
            "I can climb. What about you?",
            "What color are your shoes?",
            "Figs, plums and some grapes",
            "Do you like onion?"
    )

    private val units = listOf("Colors", "Things", "Food", "Cup of tea?", "Outside",
            "Farm", "Zoo", "Can I?", "Clothes", "Fruits", "Veggies")

    var unitName: String
    var unitText: String

    init {
        unitName = units[unitId - 1]
        unitText = unitTextList[unitId - 1]
    }
}