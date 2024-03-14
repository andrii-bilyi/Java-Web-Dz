package step.learning.dal.dao;

import step.learning.dal.dto.ActionItem;

import java.util.UUID;

public class ActionDao {
    public ActionItem[] getAction(){
        return new ActionItem[]{
             new ActionItem(UUID.randomUUID(), "IdeaPad Slim 5 16IAH8","Lenovo", "Ноутбук", 32999, 25999),
             new ActionItem(UUID.randomUUID(), "QE50Q60CAUXUA","Samsung", "Телевізор", 31999, 28999),
             new ActionItem(UUID.randomUUID(), "iPhone 15 Pro 256GB Black","Apple", "Мобільний телефон", 55999, 55499),
        };
    }
}
