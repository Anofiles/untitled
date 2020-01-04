package com.company;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;


public class ModelTable implements TableModel {

    private ArrayList <TableModelListener> listeners;

     public ModelTable(){
         listeners=new ArrayList<TableModelListener>();
     }

    @Override
    public int getRowCount() {
        // складываем их внтури класса Маин и получать статический доступ
        return Main.person.size();//	Метод возвращает количество строк в таблице.
        // равное колиству обьетков
    }
    @Override
    public int getColumnCount() {
        return 2;//Метод возвращает количество столбцов в таблице.
    }
    @Override
    public String getColumnName(int index) {
        String s="";
        //Метод определения имен колонок, которое будет отображаться в заголовке таблицы JTableHeader.
        // Заголовок таблицы появляется при размещении таблицы в панели прокрутки.
       //public int getColumnCount() узнает у этого метода количество колонок
        //и кажому присвоит индекс 0,1,2,3,4 и на этот  индекс будет возрвращать строку
        switch (index){
            case 0:
                s= "ИМЯ";
                break;
            case 1:
                s= "ФАМИЛИЯ";
                break;
        }
        return s;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Метод определения типа данных, хранимых в столбце. Тип задается в виде объекта Class.
        // На основе типа данных определяется, как следует отображать и редактировать эти данные.
        // Таблица JTable стандартно поддерживает несколько типов данных для столбцов.
        return String.class;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       //Метод определения возможности редактирования ячейки таблицы ,
        // какая редактируемая, какая нет
        return true;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Метод используется для определения значения ячейки таблицы. Реализуйте этот метод,
        // если в таблице есть редактируемые ячейки, иначе их значение невозможно будет поменять.
       // в случаее нуля выдает имя, в случае 1 выдает фамилию
        Object return_object=null;
        switch (columnIndex){//Для одного обьетка одна одна строка но две колонки ,
            //мы свичимся через колонки, ряд соответсвует каждому элементу в листе,
            // поэтому каждый раз rowIndex одни для одного обьекта, а columnIndex по два
            //в случае, 0 то нам нужно будет из обьекта соответутсвющего идекса достать
            // имя , получаем доступ, что узнать какое имя возращем, получаем доступ к листу
            // что бы получить из листа обьект , нужно  вызвать метод get который принимает индекс
            // наши индексы колоснок соответсуют идксам эрайл листа 0 1 2  3 4 строка,
            // в каждой по обьекту, и методом getName  вытягиваем имя

            case 0 :return_object=Main.person.get(rowIndex).getName();break;
            case 1 :return_object=Main.person.get(rowIndex).getSurname();break;
        }
        return return_object;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // если ячейка отредоктрирована вызывается этот после энтера, это метод  куда установить обьект
       switch (columnIndex){
           case 0:Main.person.get(rowIndex).setName((String)aValue);break;
           case 1:Main.person.get(rowIndex).setSurname((String)aValue);break;
       }

     }
    @Override
    public void addTableModelListener(TableModelListener l) {

       listeners.add(l);
      //TableModel позволяет задать Listener, который будет оповещаться при изменении данных.
      //Следовательно, класс который должен оповещаться об изменениях должен реализовать интерфейс TableModelListener и в методе tableChanged(TableModelEvent e) производить необходимые операции при наступлении события.
      //Чтобы подписать этот класс на оповещения от модели, используйте TableModel.addTableModelListener(TableModelListener l).
    }
    @Override
    public void removeTableModelListener(TableModelListener l) {
       listeners.remove(l);
    }
}
