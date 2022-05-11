package Global;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GlobalPanel extends JPanel {
    JFrame MainFrame;

    String[] namesButtons = new String[]{"Главная страница", "Студенты", "Объявления", "Список", "Добавить"};
    JButton[] MainButtons = new JButton[5];

    JPanel MainPage, StudentsList, NewStudents, NewMess;
    JScrollPane MainPageScroll, StudentsListScroll, NewStudentsScroll, NewMessScroll;

    int iterForms=0;


    GlobalPanel(JFrame frame) {
        MainFrame = frame;

        setBackground(COLOR_FONT.Frame);
        setSize(1920, 1080);
        setLayout(null);
        MainFrame.setTitle("Editor — Главная страница");
        Customizing();
        JLabel Decorate = new JLabel();
        Decorate.setBounds(0,0,180, 1080);
        Decorate.setOpaque(true);
        Decorate.setBackground(COLOR_FONT.TextArea);
        add(Decorate);
        setVisible(true);


    }
    void Customizing() {
        for (int i = 0; i < 5; ++i) {
            MainButtons[i] = new JButton(namesButtons[i]);
            MainButtons[i].setBackground(COLOR_FONT.Buttons);
            MainButtons[i].setFocusPainted(false);
            MainButtons[i].setBounds(0, i * 80, 180, 80);
            MainButtons[i].setFont(COLOR_FONT.ButtonsFont);
            MainButtons[i].setForeground(COLOR_FONT.Text);
            if (i > 2) {
                MainButtons[i].setVisible(false);
                MainButtons[i].setBackground(COLOR_FONT.Button1);
                MainButtons[i].setLocation(0, (i - 1) * 80);
            }
            MainButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i< 5; i++) {
                        if (MainButtons[i] == e.getSource()){
                            switch (i){
                                case 0:
                                {
                                    MainFrame.setTitle("Editor — Главная страница");
                                    MainButtons[3].setVisible(false); MainButtons[4].setVisible(false);
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    AddMainPage();
                                    break;
                                }

                                //При нажатии на "Студенты"
                                case 1:
                                {
                                    MainButtons[3].setVisible(true);
                                    MainButtons[4].setVisible(true);
                                    MainButtons[2].setLocation(0,320 );
                                    break;
                                }

                                //При нажатии на "Объявления"
                                case 2:
                                {
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    MainButtons[3].setVisible(false); MainButtons[4].setVisible(false);
                                    MainButtons[2].setLocation(0,160 );
                                    MainFrame.setTitle("Editor – Редактор сообщений");
                                    break;
                                }

                                //При нажатии на "Список студентов"
                                case 3:
                                {
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    MainFrame.setTitle("Editor – Список студентов");
                                    break;
                                }

                                //При нажатии на "Добавить студента"
                                case 4:
                                {
                                    iterForms=0;
                                    mainPanel = new ArrayList<>();
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    AddNewStudents();
                                    MainFrame.setTitle("Editor – Добавить студента");
                                    break;
                                }
                            }
                        }
                    }
                }
            });
            add(this.MainButtons[i]);
        }
        AddMainPage();
    }

    //Метод отображения главной страницы
    void AddMainPage (){
        //Добавляем scrollable панельку для главной страницы
        MainPage = new JPanel();
        MainPage.setLayout(null);
        MainPage.setPreferredSize(new Dimension(1740, 1150));
        MainPageScroll = new JScrollPane(MainPage);
        MainPageScroll.setBounds(180, 0,1740, 1080);
        MainPageScroll.getVerticalScrollBar().setUnitIncrement(16);
        MainPage.setBackground(new Color(66,49,58));
        MainPageScroll.setVisible(true);
        MainPageScroll.setBorder(null);
        add(MainPageScroll);

        MainButtons[2].setLocation(0,160 );

        //numWin берется из количества сообщений в базе данных
        int numWin = 7;

        JLabel Title = new JLabel("Сообщения");
        Title.setFont(COLOR_FONT.Titles);
        Title.setBounds(30, 15, 200, 30);
        Title.setForeground(COLOR_FONT.Text);
        MainPage.add(Title);

        JTextArea MainPageTextArea[] = new JTextArea[numWin];
        JScrollPane MainPageTextAreaScroll[] = new JScrollPane[numWin];
        //Добавляем TextArea для содержания сообщений в истории сообщений на главной странице
        if (numWin > 5) MainPage.setPreferredSize(new Dimension(1740, 1230+290*(numWin-5)));
        for (int j =0; j<numWin; j++) {
            MainPageTextArea[j] = new JTextArea();
            MainPageTextAreaScroll[j] = new JScrollPane(MainPageTextArea[j]);
            MainPageTextAreaScroll[j].setBounds(30, 50*(j+1)+j*200, 1650, 200);
            MainPageTextAreaScroll[j].setBorder(null);
            MainPageTextAreaScroll[j].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            MainPageTextArea[j].setBounds(30, 30*(j+1)+j*200, 465, 200);
            MainPageTextArea[j].setFont(COLOR_FONT.simple);
            MainPageTextArea[j].setBackground(COLOR_FONT.TextArea);
            MainPageTextArea[j].setForeground(COLOR_FONT.Text);
            MainPage.add(MainPageTextAreaScroll[j]);
        }
    }
    //Метод отображения страницы со списком студентов
    void AddStudentList(){

    }
    //Метод отображения страницы с добавлением студентов
    ArrayList<JPanel> mainPanel = new ArrayList<>();
    Student<String> stud = new Student<>();
    void AddNewStudents(){
        //Добавляем scrollable панельку для страницы добавления студентов
        NewStudents = new JPanel();
        NewStudents.setLayout(null);
        NewStudents.setPreferredSize(new Dimension(1740, 250));

        //Добавляем первичную форм добавления новых студентов
        newForms();
        NewStudents.add(mainPanel.get(0));

        //Добавляем кнопку и listener к ней
        JButton NewForm = new JButton("+");
        NewForm.setBounds(25, 225, 30,30);
        NewForm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        NewForm.setForeground(new Color(117, 250, 114));
        NewForm.setFocusPainted(false);
        NewForm.setBackground(COLOR_FONT.TextArea);
        NewForm.setFont(new Font("Arial Black", Font.BOLD, 30));
        NewStudents.add(NewForm);
        SendButton();

        NewForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iterForms++;
                NewStudents.setPreferredSize(new Dimension(1740, 250+250*iterForms));
                NewForm.setLocation(25, 225+250*(iterForms));
                newForms();
                NewStudents.add(mainPanel.get(iterForms));
                Dimension sizeTemp = MainFrame.getSize();
                MainFrame.pack();
                MainFrame.setSize(sizeTemp);
                //Создаем кнопку для отправки post-запроса
                SendButton();
            }
        });

        //Отображаем окно в scroll
        NewStudentsScroll = new JScrollPane(NewStudents);
        NewStudentsScroll.setBounds(180, 0,1740, 1080);
        NewStudentsScroll.getVerticalScrollBar().setUnitIncrement(16);
        NewStudents.setBackground(new Color(66,49,58));
        NewStudentsScroll.setVisible(true);
        NewStudentsScroll.setBorder(null);
        add(NewStudentsScroll);
    }

    //Метод создания формы нового студента
    void newForms (){
        JPanel mainPanel1 = new JPanel();
        mainPanel1.setBounds(25,15+iterForms*250, 500, 200);
        mainPanel1.setLayout(null);
        mainPanel1.setBackground(COLOR_FONT.TextArea);
        mainPanel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        //Создаем номер текущей формы
        JLabel numForm = new JLabel("Студент №"+(iterForms+1));
        numForm.setFont(COLOR_FONT.simple);
        numForm.setForeground(COLOR_FONT.Text);
        numForm.setBounds(30, 5, 470,20);
        mainPanel1.add(numForm);

        //Создаем формы ввода
        String [] titles = {"Имя:", "Фамилия:", "ID-Telegram:", "ID-VK:", "Group:"};
        JLabel[] labels = new JLabel[5];
        JTextField[] textFields = new JTextField[5];
        for (int i=0;i<5;i++){
            labels[i] = new JLabel(titles[i]);
            textFields[i] = new JTextField();

            labels[i].setBounds(30, 30*i+30, 100, 20);
            labels[i].setFont(COLOR_FONT.simple);
            labels[i].setForeground(COLOR_FONT.Text);
            mainPanel1.add(labels[i]);

            textFields[i].setBounds(150,30*i+30, 300, 20);
            textFields[i].setFont(COLOR_FONT.simple);
            textFields[i].setForeground(COLOR_FONT.Frame);
            textFields[i].setOpaque(true);
            textFields[i].setBackground(COLOR_FONT.Button1);
            textFields[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            mainPanel1.add(textFields[i]);

            int finalI = i;
            textFields[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stud.add(finalI, textFields[finalI].getText());
                }
            });
        }
        mainPanel.add(mainPanel1);
    }

    void SendButton (){
        JButton Send = new JButton("Отправить");
        if (iterForms == 0) Send.setBounds(60, 225, 150,30);
        else Send.setBounds(60, 225+250*(iterForms), 150,30);
        Send.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        Send.setForeground(new Color(117, 250, 114));
        Send.setFocusPainted(false);
        Send.setBackground(COLOR_FONT.TextArea);
        Send.setFont(new Font("Arial Black", Font.BOLD, 20));
        NewStudents.add(Send);
        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stud.post();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //Метод отображения редактора объявлений
    void AddNewMess(){

    }
}

