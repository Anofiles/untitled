package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MainWindow extends JFrame {
    public Person model;
    public MainWindow list;
    private JTable table;
    private ModelTable modelTable;


    public MainWindow(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(640,480);
        getContentPane().setLayout(null);


        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(10,11,480,430);
        getContentPane().add(scrollPane);

        modelTable=new ModelTable();
        table=new JTable(modelTable);// персонализируем и добавляем в конструктор модель таблицы
        scrollPane.setViewportView(table);

        JButton btnAdd=new JButton("Добавить");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person=new Person();
                person.setName(JOptionPane.showInputDialog("Введите имя"));
                person.setSurname(JOptionPane.showInputDialog("Введите фамилию"));
                Main.person.add(person);
                table.updateUI();

            }
        });
        btnAdd.setBounds(535,11,89,23);
        getContentPane().add(btnAdd);


        JButton btnRem=new JButton("Удалить");
        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow()==-1||Main.person.size()<0){return;}
                Main.person.remove(((JTable) table).getSelectedRow());
                table.updateUI();

            }
        });
        btnRem.setBounds(535,45,89,23);
        getContentPane().add(btnRem);

        setVisible(true);



    }}