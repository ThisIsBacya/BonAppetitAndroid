package com.example.bonappetitandroid

import com.example.linguaflow.R
import kotlinx.serialization.Serializable

sealed class Eat(var route: String?, var icon: Int?, var title: String?, var description: String?, var calories: Int?, var price: Int?, var num: Int?) {
    class Food {
        class ColdSnacks {
            object Line : Eat("lineCategory", R.drawable.line, "Холодные закуски", null, null, 0, null)
            class Fish {
                object Line : Eat("lineSubcategory", R.drawable.line, "Рыбные", null, null, null, null)
                object ColdFishSnack1 :
                    Eat("cold_fish_snack1", R.drawable.cold_fish_snack1, "Кольца кальмара", "Кольца, сделанные из кальмара", 150, 150, 0,)

                object ColdFishSnack2 :
                    Eat("cold_fish_snack2", R.drawable.cold_fish_snack2, "Рулетики из семги", "Рулетики из семги с творожным сыром", 180, 120, 0)

                object ColdFishSnack3 :
                    Eat("cold_fish_snack3", R.drawable.cold_fish_snack3, "Рыбное ассорти", "Карпаччо из семги, креветок и палтуса", 200, 275, 0)

                object ColdFishSnack4 :
                    Eat("cold_fish_snack4", R.drawable.cold_fish_snack4, "Рольмопс", "Рулетик из маринованного в уксусе с солью филе сельди с начинкой из корнишонов", 110, 120, 0)

                var item = mutableListOf(
                    Line,
                    ColdFishSnack1,
                    ColdFishSnack2,
                    ColdFishSnack3,
                    ColdFishSnack4
                )

            }

            class Meat {
                object Line : Eat("lineSubcategory", R.drawable.line, "Мясные", null, null, null, 0)
                object ColdMeatSnack1 :
                    Eat("cold_meat_snack1", R.drawable.cold_meat_snack1, "Мясное ассорти 1", "Ассорти из различных колбас и бекона", 200, 230, 0)

                object ColdMeatSnack2 :
                    Eat("cold_meat_snack2", R.drawable.cold_meat_snack2, "Мясное ассорти 2", "Мясное ассорти из буженины, ветчины, куриной грудки и карпаччо", 230, 270, 0)

                object ColdMeatSnack3 :
                    Eat("cold_meat_snack3", R.drawable.cold_meat_snack3, "Ростбиф", "Ростбиф из говядины с ароматными травами", 370, 430, 0)

                object ColdMeatSnack4 :
                    Eat("cold_meat_snack4", R.drawable.cold_meat_snack4, "Копченое куриное филе", "Ломтики куриного филе холодного копчения", 310, 330, 0)

                var item = mutableListOf(
                    Line,
                    ColdMeatSnack1,
                    ColdMeatSnack2,
                    ColdMeatSnack3,
                    ColdMeatSnack4
                )
            }

            class Vegetables {
                object Line : Eat("lineSubcategory", R.drawable.line, "Овощные", null, null, null, 0)
                object ColdVegSnack1 :
                    Eat("cold_veg_snack1", R.drawable.cold_veg_snack1, "Овощная нарезка", "Нарезка из овощей", 70, 120, 0)

//                object ColdVegSnack2 :
//                    Eat("cold_veg_snack2", R.drawable.cold_veg_snack2, "Рулетики из баклажана", "", 80, 210, 0)

                object ColdVegSnack3 :
                    Eat("cold_veg_snack3", R.drawable.cold_veg_snack3, "Спанакопита", "Пирог со шпинатом и сыром фета", 120, 140, 0)

                var item = mutableListOf(
                    Line,
                    ColdVegSnack1,
//                    ColdVegSnack2,
                    ColdVegSnack3
                )
            }

            var coldSnacks = mutableListOf<Eat>(Line).plus(
                Fish().item.plus(
                    Meat().item.plus(
                        Vegetables().item)))
        }

        class Salads {
            object Line : Eat("lineCategory", R.drawable.line, "Салаты", null, null, 1, null)

            class Fish {
                object Line : Eat("lineSubcategory", R.drawable.line, "Рыбные", null, null, null, 0)
//                object SaladFish1 : Eat("salad_fish1", R.drawable.salads_fish1, "Оливье", "Оливье с семгой и красной икрой", 130, 210, 0)
                object SaladFish2 : Eat("salad_fish2", R.drawable.salads_fish2, "Салат с семгой и рыбой", "Салат из копченого лосося и семги", 150, 230, 0)
                object SaladFish3 : Eat("salad_fish3", R.drawable.salads_fish3, "Салат из авокадо и рыбы", "Непритязательный салат из рыбы и зелени, но необыкновенно вкусный и очень красивый.",  170, 260, 0)
                object SaladFish4 : Eat("salad_fish4", R.drawable.salads_fish4, "Цезарь с креветками", "Традиционный салат цезарь с добавлением креветок", 230, 320, 0)

                var item = mutableListOf(
                    Line,
//                    SaladFish1,
                    SaladFish2,
                    SaladFish3,
                    SaladFish4,
                )
            }

            class Meat {
                object Line : Eat("lineSubcategory", R.drawable.line, "Мясные", null, null, null, 0)
//                object SaladMeat1 : Eat("salad_meat1", R.drawable.salads_meat1, "Салат из мяса и овощей ", "Салат из говяжьей вырезки с добавлением баклажана, болгарского перца и цукини", 170, 280, 0)
                object SaladMeat2 : Eat("salad_meat2", R.drawable.salads_meat2, "Салат «Казан»", "Салат из приготовленной в тандыре баранины, баклажанов с добавлением фасоли и красного лука", 200, 255, 0)
                object SaladMeat3 : Eat("salad_meat3", R.drawable.salads_meat3, "Мясо с овощами", "Стейк из говядины с добавлением овощей", 230, 375, 0)
                object SaladMeat4 : Eat("salad_meat4", R.drawable.salads_meat4, "Салат с языком", "Мясной салат с языком и добавлением огурцов и грибов",210, 240, 0)

                var item = mutableListOf(
                    Line,
//                    SaladMeat1,
                    SaladMeat2,
                    SaladMeat3,
                    SaladMeat4
                )
            }

            class Vegetables {
                object Line : Eat("lineSubcategory", R.drawable.line, "Овощные", null, null, null, 0)
                object SaladVeg1 : Eat("salad_veg1", R.drawable.salads_veg1, "Греческий салат", "Традиционный греческий салат с сыром фета, болграсикм перцем, помидорами и оливками", 130, 230, 0)
                object SaladVeg2 : Eat("salad_veg2", R.drawable.salads_veg2, "Греческий салат с гренками", "Греческий салат с сыром фета, гренками, помидорками черри и красным луком", 150, 245, 0)
                object SaladVeg3 : Eat("salad_veg3", R.drawable.salads_veg3, "Салат руккола", "Салат с добавлением авокадо и манго", 130, 220, 0)
                object SaladVeg4 : Eat("salad_veg4", R.drawable.salads_veg4, "Зимний салат", "Салат с добавлением томатов, соленых огурцов и малины", 130, 270, 0)

                var item = mutableListOf(
                    Line,
                    SaladVeg1,
                    SaladVeg2,
                    SaladVeg3,
                    SaladVeg4
                )
            }

            var salads = mutableListOf<Eat>(Line).plus(Fish().item.plus(Meat().item.plus(Vegetables().item)))
        }

        class HotSnacks {
            object Line : Eat("lineCategory", R.drawable.line, "Горячие закуски", null, null, 2, null)

            class Fish {
                object Line : Eat("lineSubcategory", R.drawable.line, "Рыбные", null, null, null, 0)
//                object HotFishSnack1 :
//                    Eat("hot_fish_snack1", R.drawable.hot_fish_snack1, "Филе сибаса", "Филе рыбы сибаса с брокколи, яйцом и сыром", 260, 340, 0)
                object HotFishSnack2 :
                    Eat("hot_fish_snack2", R.drawable.hot_fish_snack2, "Креветки на шампуре", "Креветки на шампуре, в гриле и с чесноком", 210, 290, 0)

                object HotFishSnack3 :
                    Eat("hot_fish_snack3", R.drawable.hot_fish_snack3, "Сашими угорь", "Маринованный угорь с добавлением креветок", 130, 220, 0)

                object HotFishSnack4 :
                    Eat("hot_fish_snack4", R.drawable.hot_fish_snack4, "Рыбное ассорти", "Ассорти из креветок, мидий и кальмара во фритюре", 210, 370, 0)

                var item = mutableListOf(
                    Line,
//                    HotFishSnack1,
                    HotFishSnack2,
                    HotFishSnack3,
                    HotFishSnack4
                )
            }

            class Meat {
                object Line : Eat("lineSubcategory", R.drawable.line, "Мясные", null, null, null, 0)
                object HotMeatSnack1 :
                    Eat("hot_meat_snack1", R.drawable.hot_meat_snack1, "Рулетики из индейки", "Рулетики из индейки с беконом и добавление розмарина", 280, 310, 0)

                object HotMeatSnack2 :
                    Eat("hot_meat_snack2", R.drawable.hot_meat_snack2, "Брускетта", "Бутерброды с беконом, болграским перцем и помидорами", 210, 290, 0)

//                object HotMeatSnack3 :
//                    Eat("hot_meat_snack3", R.drawable.hot_meat_snack3, "Вырезка оленя", "Томленая вырезка оленя с соусом из сосновых шишек", 230, 355, 0)

                var item = mutableListOf(
                    Line,
                    HotMeatSnack1,
                    HotMeatSnack2,
//                    HotMeatSnack3
                )
            }

            var hotSnacks = mutableListOf<Eat>(Line).plus(Fish().item.plus(Meat().item))
        }

        class First {
            object Line : Eat("lineCategory", R.drawable.line, "Первые блюда", null, null, 3, null)

            class Broth {
                object Line : Eat("lineSubcategory", R.drawable.line, "Бульоны", null, null, null, 0)
//                object Broth1 : Eat("broth1", R.drawable.first_broth1, "Уха", "Рыбный бульон", 130, 410, 0)
                object Broth2 : Eat("broth2", R.drawable.first_broth2, "Гороховый суп", "Гороховый суп с копченой курицей", 210, 340, 0)
                object Broth3 : Eat("broth3", R.drawable.first_broth3, "Куриный суп", "Куриный бульон", 170, 220, 0)

                var item = mutableListOf(
                    Line,
//                    Broth1,
                    Broth2,
                    Broth3
                )
            }

            class Filling {
                object Line : Eat("lineSubcategory", R.drawable.line, "Заправочные супы", null, null, null, 0)
                object Filling1 : Eat("filling1", R.drawable.first_filling1, "Солянка", "Солянка с добавлением трёх видов колбас, курицы, маслин и лимоном", 240, 450, 0)
//                object Filling2 : Eat("filling2", R.drawable.first_filling2, "Борщ", "Традиционный украинский борщ из томатной пасты с мясом", 210, 385, 0)
                object Filling3 : Eat("filling3", R.drawable.first_filling3, "Щи", "Щи с тушенкой и картошкой", 230, 420, 0)
                object Filling4 : Eat("filling4", R.drawable.first_filling4, "Грибной суп", "Суп с грибами, морковкой и зеленью", 180, 320, 0)

                var item = mutableListOf(
                    Line,
                    Filling1,
//                    Filling2,
                    Filling3,
                    Filling4
                )
            }

            class Puree {
                object Line : Eat("lineSubcategory", R.drawable.line, "Пюреобразные супы", null, null, null, 0)
                object Puree1 : Eat("puree1", R.drawable.first_puree1, "Суп из брокколи", "Крем-суп из брокколи с гренками", 130, 260, 0)
                object Puree2 : Eat("broth2", R.drawable.first_broth2, "Гороховый суп", "Гороховый суп с копченой курицей", 210, 340, 0)
                object Puree3 : Eat("puree3", R.drawable.first_puree3, "Сливочный суп ", "Фирменный сливочный суп с лососем", 310, 360, 0)
                object Puree4 : Eat("puree4", R.drawable.first_puree4, "Грибной крем-суп", "Суп-пюре с лисичками", 190, 385, 0)

                var item = mutableListOf(
                    Line,
                    Puree1,
                    Puree2,
                    Puree3,
                    Puree4
                )
            }

            var first = mutableListOf<Eat>(Line).plus(Broth().item.plus(Filling().item.plus(Puree().item)))
        }

        class Second {
            object Line : Eat("lineCategory", R.drawable.line, "Вторые блюда", null, null, 4, null)
            class Fish {
                object Line : Eat("lineSubcategory", R.drawable.line, "Рыбные", null, null, null, 0)
                object SecondFish1 :
                    Eat("second_fish1", R.drawable.second_fish1, "Морской окунь", "Вкусный жаренный окунь. Подается с брокколи", 220, 460, 0)

                object SecondFish2 :
                    Eat("second_fish2", R.drawable.second_fish2, "Форель", "Форель на гриле. Подаётся с помидорами", 310, 415, 0)

                object SecondFish3 :
                    Eat("second_fish3", R.drawable.second_fish3, "Лосось", "Лосось по-норвежски. Подается в теплом виде", 240, 430, 0)

                object SecondFish4 :
                    Eat("second_fish4", R.drawable.second_fish4, "Стейк из лосося", "Стейл из лосося с гарниром из овощей", 270, 350, 0)
                object SecondFish5 :
                    Eat("second_fish5", R.drawable.second_fish5, "Стейк из семги", "Стейл из семги с гарниром из овощей", 310, 400, 0)


                var item = mutableListOf(
                    Line,
                    SecondFish1,
                    SecondFish2,
                    SecondFish3,
                    SecondFish4,
                    SecondFish5
                )
            }

            class Meat {
                object Line : Eat("lineSubcategory", R.drawable.line, "Мясные", null, null, null, null)
                object SecondMeat1 :
                    Eat("second_meat1", R.drawable.second_meat1, "Запеченная курица", "Запеченный окорок курицы с баклажанами", 310, 470, 0)

                object SecondMeat2 :
                    Eat("second_meat2", R.drawable.second_meat2, "Стейк из говядины", "Стейк из говядины well done прожарки", 330, 440, 0)

                object SecondMeat3 :
                    Eat("second_meat3", R.drawable.second_meat3, "Рыбный стейк", "Стейк из свинины well done прожарки", 300, 385, 0)

                var item = mutableListOf(
                    Line,
                    SecondMeat1,
                    SecondMeat2,
                    SecondMeat3
                )
            }

            class Vegetables {
                object Line : Eat("lineSubcategory", R.drawable.line, "Овощные", null, null, null, 0)
                object SecondVeg1 : Eat("second_veg1", R.drawable.second_veg1, "Фаршированные баклажаны", "Запеченные баклажаны с добавлением говяжьего фарша", 240, 325, 0)
                object SecondVeg2 : Eat("second_veg2", R.drawable.second_veg2, "Рулетики сибаса", "Рулетики с запеченными кабачками и перцем", 160, 270, 0)
                object SecondVeg3 : Eat("second_veg3", R.drawable.second_veg3, "Рататуй", "Салат из баклажанов, болграского перца и свежего огурца", 165, 300, 0)
                object SecondVeg4 : Eat("second_veg4", R.drawable.second_veg4, "Жареный картофель", "Жареный картофель с грибами", 230, 355, 0)
                object SecondVeg5 : Eat("second_veg5", R.drawable.second_veg5, "Гриль-салат", "Гриль-салат с цукини, помидорами, болгарксим перцем и баклажанами", 130, 340, 0)

                var item = mutableListOf(
                    Line,
                    SecondVeg1,
                    SecondVeg2,
                    SecondVeg3,
                    SecondVeg4,
                    SecondVeg5
                )
            }

            var second = mutableListOf<Eat>(Line).plus(Fish().item.plus(Meat().item.plus(Vegetables().item)))
        }

        class Garnish {
            object Line : Eat("lineCategory", R.drawable.line, "Гарниры", null, null, 5, null)
            object Garnish1 : Eat("garnish1", R.drawable.garnish1, "Мясо по-французски", "Свинина, покрытая сыром. Подается с овощами", 260, 310, 0)
//            object Garnish2 : Eat("garnish2", R.drawable.garnish2, "Запеченные овощи", "Овощи, сделанные на гриле. Подаются в горячем виде", 150, 240, 0)
            object Garnish3 : Eat("garnish3", R.drawable.garnish3, "Печеный картофель", "Печеный картофель с соусом песто", 130, 220, 0)
            object Garnish4 : Eat("garnish4", R.drawable.garnish4, "Ассорти", "Ассорти из печеного картофеля и овощей", 225, 265, 0)

            var item = mutableListOf(
                Line,
                Garnish1,
//                Garnish2,
                Garnish3,
                Garnish4
            )
        }

        class Desserts {
            object Line : Eat("lineCategory", R.drawable.line, "Десерты", null, null, 6, null)
            object Desserts1 : Eat("desserts1", R.drawable.desserts1, "Тирамису", "Тирамису, политое мёдом", 150, 200, 0)
            object Desserts2 : Eat("desserts2", R.drawable.desserts2, "Мороженое", "Три шарика мороженого на выбор", 175, 230, 0)
            object Desserts3 : Eat("desserts3", R.drawable.desserts3, "Шоколадный десерт", "Десерт расплавленного шоколада с добавлением карамели", 130, 265, 0)
            object Desserts4 : Eat("desserts4", R.drawable.desserts4, "Торт «Сент-Оноре»", "Тортик с клубникой, соленой карамелью и мадагаскарской ванилью",  200, 230, 0)
            object Desserts5 : Eat("desserts5", R.drawable.desserts5, "Чизкейк", "Классический чизкейк, политый шоколадом", 130, 240, 0)

            var item = mutableListOf(
                Line,
                Desserts1,
                Desserts2,
                Desserts3,
                Desserts4,
                Desserts5
            )
        }

        class Drinks {
            object Line : Eat("lineCategory", R.drawable.line, "Напитки", null, null, 7, null)
            class Cold {
                object Line : Eat("lineSubcategory", R.drawable.line, "Холодные", null, null, null, 0)
                object DrinksCold1 :
                    Eat("drinks_cold1", R.drawable.drinks_cold1, "Мохито", "Безалкогольный мохито на выбор", 70, 230, 0)

                object DrinksCold2 :
                    Eat("drinks_cold2", R.drawable.drinks_cold2, "Коктейль по-бразильски", "", 80, 200, 0)

                object DrinksCold3 :
                    Eat("drinks_cold3", R.drawable.drinks_cold3, "Молекулярный коктейль", "Легкий летний коктейль", 55, 250, 0)

                object DrinksCold4 :
                    Eat("drinks_cold4", R.drawable.drinks_cold4, "Холодный чай", "Черный холодный чай", 40, 200, 0)

                var item = mutableListOf(
                    Line,
                    DrinksCold1,
                    DrinksCold2,
                    DrinksCold3,
                    DrinksCold4
                )
            }

            class Hot {
                object Line : Eat("lineSubcategory", R.drawable.line, "Горячие", null, null, null, 0)
                object DrinksHot1 : Eat("drinks_hot1", R.drawable.drinks_hot1, "Глинтвейн", "Классический глинтвейн",130, 240, 0)
                object DrinksHot2 : Eat("drinks_hot2", R.drawable.drinks_hot2, "Горячий шоколад", "Горячий шоколад с соленой карамелью", 160, 270, 0)
                object DrinksHot3 : Eat("drinks_hot3", R.drawable.drinks_hot3, "Чай", "Чай с добавлением кориандра и корицы", 100, 120, 0)
                object DrinksHot4 : Eat("drinks_hot4", R.drawable.drinks_hot4, "Латте", "Латте", 130, 240, 0)

                var item = mutableListOf(
                    Line,
                    DrinksHot1,
                    DrinksHot2,
                    DrinksHot3,
                    DrinksHot4
                )
            }

            class Alcohol {
                object Line : Eat("lineSubcategory", R.drawable.line, "Алкогольные", null, null, null, 0)
                object DrinksAlc1 : Eat("drinks_alc1", R.drawable.drinks_alc1, "Виски", "Стакан виски со льдом", 250, 350, 0)
                object DrinksAlc2 : Eat("drinks_alc2", R.drawable.drinks_alc2, "Мартини", "Бокал мартини со льдом", 160, 330, 0)
                object DrinksAlc3 : Eat("drinks_alc3", R.drawable.drinks_alc3, "Шоты", "Шоты на основе бейлиз", 130, 150, 0)
                object DrinksAlc4 : Eat("drinks_alc4", R.drawable.drinks_alc4, "Джин-тоник", "Джин-тоник со вкусом на выбор", 150, 180, 0)

                var item = mutableListOf(
                    Line,
                    DrinksAlc1,
                    DrinksAlc2,
                    DrinksAlc3,
                    DrinksAlc4
                )
            }

            var drinks = mutableListOf<Eat>(Line).plus(Cold().item.plus(Hot().item.plus(Alcohol().item)))
        }

        var food = ColdSnacks().coldSnacks.plus(
            Salads().salads.plus(
                HotSnacks().hotSnacks.plus(
                    First().first.plus(
                        Second().second.plus(
                            Garnish().item.plus(
                                Desserts().item.plus(
                                    Drinks().drinks
                                )
                            )
                        )
                    )
                )
            )
        )
    }


}
@Serializable
data class Food(
    var route: String?,
    var icon: Int?,
    var title: String?,
    var description: String?,
    var calories: Int?,
    var price: Int?,
    var num: Int?,
)
@Serializable
data class FoodWithoutIcon(
    var route: String?,
    var title: String?,
    var description: String?,
    var calories: Int?,
    var price: Int?,
    var num: Int?,
)

