package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.services.db.DbService;
import step.learning.services.db.MySqlDbService;
import step.learning.services.form.FormParseService;
import step.learning.services.form.HybridFormParser;
import step.learning.services.hash.HashService;
import step.learning.services.hash.Md5HashService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // конфігурація служб
        // "Буде запит на HashService -- повернути об'єкт Md5HashService"
        bind(HashService.class).to(Md5HashService.class); // ASP: Service.AddSingleton<Hash,Md5>
        bind(DbService.class).to(MySqlDbService.class);
        bind(FormParseService.class).to(HybridFormParser.class);
    }
}
/*
Інверсія управління (Inversion of Control, IoC) - це архітектурний
стиль згідно з яким питання життєвого циклу об'єктів вирішуються
окемим спеціальним модулем (контейнером залежностей, інжектором).
Життєвий цикл об'єкту - CRUD, у простішому випадку мова іде про
створення (С) об'єктів: чи створювати новий інстанс класу / чи залишати
раніше створений.
Також на модуль IoC покладається задача заповнення (Resolve) об'єктів -
впровадження (Inject) у них залежностей - посилань на інші об'єкти,
що їх створює IoC.

Без IoC                          З ІоС
class Klass {                    class Klass {
service = new Service()          @Inject Service service
...                              ....
}                                }
...
k1 = new Klass()                  k1 = Injector.Resolve(Klass)
k2 = new Klass()                  k2 = Injector.Resolve(Klass)
 у к1 та к2 різні service          у к1 та к2 однакові service
 */
/*
Впровадження на базі Google Guice
Spring - аналог
- підключаємо до проєкту
    <!-- https://mvnrepository.com/artifact/com.google.inject/guice -->
    <!-- https://mvnrepository.com/artifact/com.google.inject.extensions/guice-servlet -->
- створюємо клас - "слухач" створення контексту (розгортання застосунку)
   (див. IocContextListener)
- створюємо класи-конфігуратори:
   = ServiceModule(цей клас) - для налаштування служб за DIP (з SOLID)
       згідно з яким залежності слід створювати не від класів, а від
       інтерфейсів. Але, оскільки об'єкт з інтерфейсу створити неможна,
       необхідно встановити зв'язок (bind) між інтерфейсом та його реалізацією (класом)
   = RouterModule - для налаштування маршрутизації сервлетів.
       створюємо інструкції маршрутизації (див. клас) та замінюємо
       сервлетні анотації (@WebServlet) на @Singleton для всіх сервлетів.
- змінюємо налаштування сервера (див. web.xml)
 */