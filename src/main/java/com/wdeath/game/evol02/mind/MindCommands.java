package com.wdeath.game.evol02.mind;

import com.wdeath.game.evol02.utill.ClassFinder;

import java.util.HashMap;
import java.util.List;

public class MindCommands {

    public static HashMap<Integer, MindCommand> minds;

    public static void init(){
        minds = new HashMap<>();
        load();
    }

    private static void load(){
        List<Class<?>> list = ClassFinder.find("com.wdeath.game.evol02.mind.mindcs");
        for(Class<?> clazz : list){
            add(clazz);
        }
    }

    private static void add(Class<?> clazz){
        int id = getID(clazz);
        minds.put(id, newInstance(clazz));
    }

    public static MindCommand newInstance(Class<?> clazz){
        try {
            return (MindCommand)clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getID(Class<?> clazz){
        IMind mind = clazz.getAnnotation(IMind.class);
        return mind.id();
    }
}
