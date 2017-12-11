# Содержание
* [Задание](#zadanie)
* [Введение](#vvedenie)
* [Струра данных](#sd)
* [Метод цепочек](#mc)
* [Метод открытой адресации](#moa)
    * [Линейнон пробирование](#lp)
    * [Двойное хеширование](#dh)
* [Сравнение](#comp)
* [Формат входных данных](#fvd)
* [План выполнения домашнего задания](#pvdz)
*****  
<a name="zadanie"></a>
# Задание
  Сравнить хэш-таблицы с различными способами разрешения коллизий:
  метод цепочек, метод двойного хэширования и метод линейного пробирования.

<a name="vvedenie"></a>
# Введение
Хеширование – преобразование ключей к числам.

Хеш-таблица – массив ключей с особой логикой, состоящей из:
    1. Вычисления хеш-функции, которая преобразует ключ поиска в индекс.
    2. Разрешения конфликтов, т.к. два и более различных ключа могут преобразовываться в один и тот же индекс массива.
    Отношение порядка над ключами не требуется.

Хеш-функция ― преобразование по детерминированному алгоритму входного массива данных произвольной длины (один ключ) в выходную битовую строку фиксированной длины (значение). Результат вычисления хеш-фукнции называют «хешем».

  Хеш-функция должна иметь следующие свойства:

  * Всегда возвращать один и тот же адрес для одного и того же ключа;
  * Не обязательно возвращает разные адреса для разных ключей;
  * Использует все адресное пространство с одинаковой вероятностью;
  * Быстро вычислять адрес.

Коллизией хеш-функции `H` называется два различных входных блока данных `X` и `Y` таких, что `H(x) = H(y)`.

<a name="sd"></a>
# Структкра данных

Хеш-таблица – структура данных, хранящая ключи в таблице. Индекс ключа вычисляется с помощью хеш-функции. Операции: добавление, удаление, поиск.
Пусть хеш-таблица имеет размер `M`, количество элементов в хеш-таблице – `N`.

Число хранимых элементов, делённое на размер массива (число возможных значений хеш-функции), называется коэффициентом заполнения хеш-таблицы (load factor). Обозначим его `α = N/M`.

Этот коэффициент является важным параметром, от которого зависит среднее время выполнения операций.

Парадокс дней рождений. При вставке в хеш-таблицу размером 365 ячеек всего лишь 23-х элементов вероятность коллизии уже превысит 50 % (если каждый элемент может равновероятно попасть в любую ячейку).

Хеш-таблицы различаются по методу разрешения коллизий.
Основные методы разрешения коллизий:
1. Метод цепочек.
2. Метод открытой адресации.


<a name="mc"></a>
# Метод цепочек
Каждая ячейка массива является указателем на связный список (цепочку). Коллизии приводят к тому, что появляются цепочки длиной более одного элемента.

Добавление ключа.
1. Вычисляем значение хеш-функции добавляемого ключа – `h`.
2. Находим `A[h]` – указатель на список ключей.
3. Вставляем в начало списка (в конец списка дольше). Если запрещено дублировать ключи, то придется просмотреть весь список.

Время работы:
В лучшем случае – `O(1)`.
В худшем случае
– если не требуется проверять наличие дубля, то `O(1)`
– иначе – `O(N)`

Удаление ключа.
1. Вычисляем значение хеш-функции удаляемого ключа – `h`.
2. Находим `A[h]` – указатель на список ключей.
3. Ищем в списке удаляемый ключ и удаляем его.

Время работы:
В лучшем случае – `O(1)`.
В худшем случае – `O(N)`.

Поиск ключа.
1. Вычисляем значение хеш-функции ключа – `h`.
2. Находим `A[h]` – указатель на список ключей.
3. Ищем его в списке.

Время работы:
В лучшем случае – `O(1)`.
В худшем случае – `O(N)`.

Среднее время работы операций поиска, вставки (с проверкой на дубликаты) и удаления в хеш-таблице, реализованной методом цепочек – `O(1 + α)`, где `α` – коэффициент заполнения таблицы.

Среднее время работы.

Теорема. 

Среднее время работы операций поиска, вставки (с
проверкой на дубликаты) и удаления в хеш-таблице, реализованной
методом цепочек – O(1 + α), где α – коэффициент заполнения
таблицы.

Доказательство. Среднее время работы – математическое ожидание
времени работы в зависимости от исходного ключа.
Время работы для обработки одного ключа `T(k)` зависит от длины
цепочки и равно `1 + Nh(k)` , где `Ni` – длина i-ой цепочки.
Предполагаем, что хеш-функция равномерна, а ключи
равновероятны.

Среднее время работы

![formula](https://latex.codecogs.com/png.latex?\dpi{100}&space;\large&space;Tcp(M,&space;N)&space;=&space;M(T(k))&space;=&space;\sum_{i&space;=&space;0}^{M&space;-1}\frac{1}{M}*(1&space;&plus;&space;Ni)&space;=&space;\frac{1}{M}\sum_{i&space;=&space;0}^{M&space;-&space;1}(1&space;&plus;&space;Ni)&space;=&space;\frac{M&space;&plus;&space;N}{M}&space;=&space;1&space;&plus;&space;\alpha)
<a name="moa"></a>
# Метод открытой адресации

Все элементы хранятся непосредственно в массиве. Каждая запись в массиве содержит либо элемент, либо `NIL`. При поиске элемента систематически проверяем ячейки до тех пор, пока не найдем искомый элемент или не убедимся в его отсутствии.

При поиске элемента систематически проверяем ячейки до
тех пор, пока не найдем искомый элемент или не убедимся в
его отсутствии.

Вставка ключа.
1. Вычисляем значение хеш-функции ключа – `h`.
2. Систематически проверяем ячейки, начиная от `A[h]`, до тех пор, пока не находим пустую ячейку.
3. Помещаем вставляемый ключ в найденную ячейку. В п.2 поиск пустой ячейки выполняется в некоторой последовательности. Такая последовательность называется «последовательностью проб».

Последовательность проб зависит от вставляемого в таблицу ключа. Для определения исследуемых ячеек расширим хеш-функцию, включив в нее номер пробы (от `0`).

`h ∶ U × {0, 1, ... , M − 1} → {0, 1, ... , M − 1 }`

Важно, чтобы для каждого ключа `k` последовательность проб `h(k, 0), h(k, 1), ... , h(k, M − 1)` представляла собой перестановку множества `0,1, ... , M − 1` , чтобы могли быть просмотрены все ячейки таблицы.

Поиск ключа.
Исследуется та же последовательность, что и в алгоритме вставки ключа. Если при поиске встречается пустая ячейка, поиск завершается неуспешно, поскольку искомый ключ должен был бы быть вставлен в эту ячейку в последовательности проб, и никак не позже нее.

Удаление ключа.
Алгоритм удаления достаточен сложен. Нельзя при удалении ключа из ячейки i просто пометить ее значением `NIL`. Иначе в последовательности проб для некоторого ключа (или некоторых) возникнет пустая ячейка, что приведет к неправильной работе алгоритма поиска.
Решение. Помечать удаляемые ячейки спец. значением `«Deleted»`. Нужно изменить методы поиска и вставки. В методе вставки проверять `«Deleted»`, вставлять на его место. В методе поиска продолжать поиск при обнаружении `«Deleted»`.

Вычисление последовательности проб.
Желательно, чтобы для различных ключей `k` последовательность проб `h(k, 0), h(k, 1), ... , h(k, M − 1)` давала большое количество последовательностей- перестановок множества `0,1,... , M − 1` .

Обычно используются три метода построения `h(k, i)`:
1. Линейное пробирование.
2. Квадратичное пробирование.
3. Двойное хеширование.

<a name="lp"></a>
## Линейное пробирование

`h(k, i) = (h′(k) + i) mod M`

Основная проблема – кластеризация. Последовательность подряд идущих занятых элементов таблицы быстро увеличивается, образуя кластер. Попадание в элемент кластера при добавлении гарантирует «одинаковую прогулку» для различных ключей и проб. Новый элемент будет добавлен в конец кластера, увеличивая его.

<a name="dh"></a>
## Двойное хеширование
`h(k, i) = (h1(k) + ih2(k)) mod M.`

Требуется, чтобы последовательность проб содержала все индексы `0, .. , M–1`. Для этого все значения `h2(k)` должны быть взаимно простыми с `M`.
- M может быть степенью двойки, а `h2(k)` всегда возвращать нечетные числа.
- M простое, а `h2(k)` меньше `M`.

Общее количество последовательностей проб  `O(M^2)`.

<a name="comp"></a>
#  Сравнение
|  | Лучший случай | В среднемю Метод цепочек. | В среднем. Метод открытой адресации.| Худший случай. |
| ------ | ------ | ------ | ------ | ------ |
| Поиск | `O(1)` | `O(1 + a)` | `O(1 / 1 - a)` | `O(n)`
| Вставка | `O(1)` | `O(1 + a)` | `O(1 / 1 - a)` | `O(n)`
| Удаление | `O(1)` | `O(1 + a)` | `O(1 / 1 - a)` | `O(n)`

<a name="fvd"></a>
#  Формат входных данных
Входной файл содержит последовательность команд, т.е. представляет набор строк вида
`command [key] [data]`
где command — add, delete, search, min, max, print или спец. команда;
key — ключ, целое число;
data — данные, целое число.

Примеры:
`add 10 20`
`search 15`
`print`
`min`

#Print

Команда print отражает внутреннее строение структуры данных. Для хэш-таблицы с методом цепочек вывод команды print будет
иметь следующий вид:

`Hash table: [ {  90  240  77 } {  416  } {  } {  557  99  } {  246  } {  556  } {  267  } {  947  } ]`

{} - отдельная ячейка массива, в которой храниться список ключей.
А для хеш-таблиц с открытой адресацией вывод будет выглядеть так:

`Hash table: [ 755  D  __  855  __  942  __  346  __  588  __  576 ]`

__ - пустая ячейка, D - удаленная ячейка.

# Формат выходных данных

В выходной файл выводятся результаты исполнения программы. 

Команда *print* должна отражать внутреннее строение структуры данных

Команда *search* выводит error, в случае, когда значение не было найдено, или значение найденное по ключу в противном случае.

Команда *add* добавляет значение в таблицу (Ничего не выводит).

Команда *delete* удаляетя число, или ничего не делает, если значение не было найдено (Ничего не выводит).

Команда *min* выводит минимальное число в таблице, или empty, если таблица пустая.

Команда *max* выводит максимальное число в таблице,или empty, если таблица пустая.

# Тесты

Было создано три вида тестов. Одни проверяют корректность работы методов каждой хеш-таблицы, вторые для провеки времени выполнения большого числа операций добавления, поиска и удаления.

##### 1.Юнит тесты, проверящие корректность работы методов каждой хеш-таблицы
Тесты находятся в *./src/test/java/tests/*

##### 2.Юнит тесты для провеки времени выполнения большого числа операций добавления, поиска и удаления.
Тесты находятся в *./src/test/java/tests/*

##### 3.Файлы с входными данными и правильными ответами.

На вход программе подается два файла. 

1) Входной файл.
2) Выходной файл с результатами исполнения программы.

Заитем выходной файл с результатами исполнения программы сравнивается с файлом в котором находятся правильные ответы

Тесты находятся в *./tests/*

#Сравнение времени работы
Время работы рачитываем тестом с 100000 операций каждого типа.

Метод цепочек:

Add time: 56

Search time: 26

Delete time: 10

Линейное пробирование:

Add time: 40

Search time: 13

Delete time: 46

Двойное хэштрование:

Add time: 28

Search time: 47

Delete time: 64

<a name="pvdz"></a>
#  План выполнения домашнего задания
    1.Реализовать хеш-таблицу с методом цепочек
    2.Реализовать хеш-таблицу с двойным хешированием
    3.Реализовать хеш-таблицу с линейным пробированием
    4.Тесты
    5.Сравнение производительности и расхода памяти
    6.Анализ полученных результатов и вывод
    7.???
    8.Profit
