NameActivity, SurnameActivity, AgeActivity принадлежат одному taskAffinity, отличному от taskAffinity главного стартового экрана. Все экраны запускаются в одной задаче.

При нажатии кнопки "Назад" у экрана вызывается `finish()`, тогда из стека берется прошлый экран.

При нажатии кнопки "Закрыть" вызывается `finishAffinity()`, что закрывает весь наш флоу регистрации. Тогда из стека берется наш единственный оставшийся главный экран.

На главном экране стоит singleTask launchMode. Это позволяет нам при нажатии на кнопку "Далее" в AgeActivity просто запустить наш главный экран, передав в intent данные пользователя. Этот intent можно обработать в `onNewIntent()`. Все Activity, которые были найдены в стеке поверх главного экрана, будут учичтожены благодаря заданному launchMode.
