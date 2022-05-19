package classes;


import org.python.util.PythonInterpreter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalPanel extends JPanel {
    JFrame MainFrame;
    Dimension sizeTemp;
    String[] namesButtons = new String[]{"Главная страница", "Студенты", "Объявления", "Список", "Добавить"};
    JButton[] MainButtons = new JButton[5];

    JPanel MainPage, StudentsList, NewStudents, NewMess;
    JScrollPane MainPageScroll, StudentsListScroll, NewStudentsScroll;

    int iterForms=0;


    public GlobalPanel(JFrame frame) {
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
        sizeTemp = MainFrame.getSize();
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
                                    if (StudentsListScroll!=null) {
                                        StudentsListScroll.setVisible(false);
                                        StudentsList.setVisible(false);
                                    }
                                    if (NewMess!=null) {
                                        NewMess.setVisible(false);
                                    }
                                    try {
                                        AddMainPage();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    MainFrame.pack();
                                    MainFrame.setSize(sizeTemp);
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
                                    if (NewMess!=null) {
                                        NewMess.setVisible(false);
                                    }
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    if (StudentsListScroll!=null) {
                                        StudentsListScroll.setVisible(false);
                                        StudentsList.setVisible(false);
                                    }
                                    MainButtons[3].setVisible(false); MainButtons[4].setVisible(false);
                                    MainButtons[2].setLocation(0,160 );

                                    try {
                                        AddNewMess();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                    MainFrame.pack();
                                    MainFrame.setSize(sizeTemp);
                                    MainFrame.setTitle("Editor – Редактор сообщений");
                                    break;
                                }

                                //При нажатии на "Список студентов"
                                case 3:
                                {
                                    if (StudentsListScroll!=null) {
                                        StudentsListScroll.setVisible(false);
                                        StudentsList.setVisible(false);
                                    }
                                    if (NewMess!=null) {
                                        NewMess.setVisible(false);
                                    }
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    try {
                                        AddStudentList();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                    MainFrame.pack();
                                    MainFrame.setSize(sizeTemp);
                                    MainFrame.setTitle("Editor – Список студентов");
                                    break;
                                }

                                //При нажатии на "Добавить студента"
                                case 4:
                                {
                                    if (NewStudentsScroll!=null) {
                                        NewStudents.setVisible(false);
                                        NewStudentsScroll.setVisible(false);
                                    }
                                    if (NewMess!=null) {
                                        NewMess.setVisible(false);
                                    }
                                    iterForms=0;
                                    mainPanel = new ArrayList<>();
                                    if (MainPageScroll!=null) {
                                        MainPageScroll.setVisible(false);
                                        MainPage.setVisible(false);
                                    }
                                    if (StudentsListScroll!=null) {
                                        StudentsListScroll.setVisible(false);
                                        StudentsList.setVisible(false);
                                    }
                                    AddNewStudents();
                                    MainFrame.pack();
                                    MainFrame.setSize(sizeTemp);
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
        try {
            AddMainPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод отображения главной страницы
    void AddMainPage () throws IOException{
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
        StringBuilder str = new StringBuilder();
        URL url = null;
        url = new URL("https://buldakovn.pythonanywhere.com/messagesTelegramm");
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            for (String str2; (str2 = reader.readLine()) != null; ) {
                str.append(str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> text = null;
        if (String.valueOf(str).length() > 0) text = parseJsonMainPage(str);

        JLabel Title = new JLabel("Сообщения");
        Title.setFont(COLOR_FONT.Titles);
        Title.setBounds(30, 15, 200, 30);
        Title.setForeground(COLOR_FONT.Text);
        MainPage.add(Title);

        JTextArea MainPageTextArea[] = new JTextArea[30];
        JScrollPane MainPageTextAreaScroll[] = new JScrollPane[30];
        //Добавляем TextArea для содержания сообщений в истории сообщений на главной странице
        int j=0;
        if (String.valueOf(str).length() > 0) {
            for (Map.Entry<String, String> e : text.entrySet()) {
                j++;
                MainPage.setPreferredSize(new Dimension(1740, 1230 + 290 * (j - 4)));
                MainPageTextArea[j] = new JTextArea();
                MainPageTextAreaScroll[j] = new JScrollPane(MainPageTextArea[j]);
                MainPageTextAreaScroll[j].setBounds(30, 50 * j + (j - 1) * 200, 1650, 200);
                MainPageTextAreaScroll[j].setBorder(null);
                MainPageTextAreaScroll[j].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                MainPageTextArea[j].setBounds(30, 30 * j + (j - 1) * 200, 465, 200);
                MainPageTextArea[j].setFont(COLOR_FONT.simple);
                MainPageTextArea[j].setBackground(COLOR_FONT.TextArea);
                MainPageTextArea[j].setForeground(COLOR_FONT.Text);
                String strTemp = e.getValue() + "\n\n" + e.getKey();
                MainPageTextArea[j].setText(strTemp);
                MainPage.add(MainPageTextAreaScroll[j]);
            }
        }
    }
    //Парсинг сообщений из запросов - для главной страницы
    Map<String, String> parseJsonMainPage(StringBuilder stringBuilder){
        Map<String, String> FinalStr = new HashMap<>();
        StringBuilder tempText, tempDate;
        String st = String.valueOf(stringBuilder);
        st = String.valueOf(convertUnicode(st));
        int i=0;
        while(i!=st.length()){
            while(st.charAt(i) != 't'){
                i++;
            }
            i+=5;
            tempText = new StringBuilder();
            while (st.charAt(i)!='\"'){
                tempText.append(st.charAt(i));
                i++;
            }
            while (st.charAt(i) != 'e'){
                i++;
            }
            i+=5;
            tempDate = new StringBuilder();
            while (st.charAt(i)!='\"'){
                tempDate.append(st.charAt(i));
                i++;
            }
            FinalStr.put(String.valueOf(tempText), String.valueOf(tempDate));
            while(st.charAt(i)!='}'){
                i++;
            }
            if (st.charAt(i+1) ==']') break;
        }
        return FinalStr;
    }
    //Декодер запроса
    StringBuilder convertUnicode(String st){
        StringBuilder sb = new StringBuilder(st.length());
        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            if (ch == '\\') {
                char nextChar = (i == st.length() - 1) ? '\\' : st
                        .charAt(i + 1);
                // Octal escape?
                if (nextChar >= '0' && nextChar <= '7') {
                    String code = "" + nextChar;
                    i++;
                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                            && st.charAt(i + 1) <= '7') {
                        code += st.charAt(i + 1);
                        i++;
                        if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                                && st.charAt(i + 1) <= '7') {
                            code += st.charAt(i + 1);
                            i++;
                        }
                    }
                    sb.append((char) Integer.parseInt(code, 8));
                    continue;
                }
                switch (nextChar) {
                    case '\\':
                        ch = '\\';
                        break;
                    case 'b':
                        ch = '\b';
                        break;
                    case 'f':
                        ch = '\f';
                        break;
                    case 'n':
                        ch = '\n';
                        break;
                    case 'r':
                        ch = '\r';
                        break;
                    case 't':
                        ch = '\t';
                        break;
                    case '\"':
                        ch = '\"';
                        break;
                    case '\'':
                        ch = '\'';
                        break;
                    // Hex Unicode: u????
                    case 'u':
                        if (i >= st.length() - 5) {
                            ch = 'u';
                            break;
                        }
                        int code = Integer.parseInt(
                                "" + st.charAt(i + 2) + st.charAt(i + 3)
                                        + st.charAt(i + 4) + st.charAt(i + 5), 16);
                        sb.append(Character.toChars(code));
                        i += 5;
                        continue;
                }
                i++;
            }
            sb.append(ch);
        }
        return sb;
    }

    Map<Integer, ArrayList<String>> msg;
    //Метод отображения страницы со списком студентов
    void AddStudentList() throws IOException{
        StudentsList = new JPanel();
        StudentsList.setLayout(null);
        StudentsList.setPreferredSize(new Dimension(1740, 50));
        StudentsListScroll = new JScrollPane(StudentsList);
        StudentsListScroll.setBounds(180, 0,1740, 1080);
        StudentsListScroll.getVerticalScrollBar().setUnitIncrement(16);
        StudentsList.setBackground(new Color(66,49,58));
        StudentsListScroll.setVisible(true);
        StudentsListScroll.setBorder(null);
        add(StudentsListScroll);

        StringBuilder str = new StringBuilder();
        URL url = null;
        url = new URL("https://buldakovn.pythonanywhere.com/getStudents");
        HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
        connection1.setDoInput(true);
        connection1.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection1.getInputStream()))) {
            for (String str2; (str2 = reader.readLine()) != null; ) {
                str.append(str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        msg = parseStudents(str);
        ArrayList<String> tempArr;
        JTextField[] labels;
        //Строим панель на основе списка
        int j=0;

        JLabel[] titles = new JLabel[6];
        String[] titles_names = {"Id", "Имя", "Фамилия", "Id Вконтакте", "Id Телеграмм", "Учебная группа"};
        for (int i =0; i<6; i++){
            titles[i] = new JLabel(titles_names[i]);
            titles[i].setBounds(20+(i*150), 20, 150, 40);
            titles[i].setFont(COLOR_FONT.simple);
            titles[i].setForeground(COLOR_FONT.Frame);
            titles[i].setOpaque(true);
            titles[i].setBackground(COLOR_FONT.Button1);
            titles[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            titles[i].setHorizontalAlignment(SwingConstants.CENTER);
            StudentsList.add(titles[i]);
        }

        for (Map.Entry<Integer, ArrayList<String>> e : msg.entrySet()) {
            tempArr = e.getValue();
            labels = new JTextField[6];
            if (j>21) StudentsList.setPreferredSize(new Dimension(1740, 62+(3+j)*41));

            labels[5] = new JTextField(String.valueOf(e.getKey()));
            labels[5].setBounds(20, 62+(j*41), 150, 40);
            labels[5].setFont(COLOR_FONT.simple);
            labels[5].setForeground(COLOR_FONT.Frame);
            labels[5].setOpaque(true);
            labels[5].setBackground(new Color(190, 100, 80));
            labels[5].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            labels[5].setHorizontalAlignment(SwingConstants.CENTER);
            StudentsList.add(labels[5]);

            for (int i=0;i<5; i++) {
                labels[i] = new JTextField(tempArr.get(i));
                labels[i].setBounds(170+(i*150), 62+(j*41), 150, 40);
                labels[i].setFont(COLOR_FONT.simple);
                labels[i].setForeground(COLOR_FONT.Frame);
                labels[i].setOpaque(true);
                labels[i].setBackground(new Color(200, 110, 90));
                labels[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                labels[i].setHorizontalAlignment(SwingConstants.CENTER);
                StudentsList.add(labels[i]);
            }
            j++;
        }
    }

    Map<Integer, ArrayList<String>> parseStudents(StringBuilder stringBuilder){
        Map<Integer, ArrayList<String>> msg = new HashMap<>();
        ArrayList<String> tempArr;
        StringBuilder[] temp = new StringBuilder[6];
        String st = String.valueOf(stringBuilder);
        st = String.valueOf(convertUnicode(st));
        int i =0;
        while(st.charAt(i)!=']'){
            for (int k =0;k<6; k++) {
                temp[k] = new StringBuilder();
                while (st.charAt(i) != ':') i++;
                if (k>0) {
                    i += 3;
                    while (true) {
                        if (st.charAt(i) == '"' && st.charAt(i + 1) == ',' || st.charAt(i) == '"' && st.charAt(i + 1) == '}') {
                            i += 2;
                            break;
                        }
                        temp[k].append(st.charAt(i));
                        i++;
                    }
                }
                else {
                    i+=2;
                    while (true) {
                        if (st.charAt(i) == ',') {
                            break;
                        }
                        temp[k].append(st.charAt(i));
                        i++;
                    }
                }
            }
            tempArr = new ArrayList<>();
            for (int j = 1; j<6; j++) {
                tempArr.add(String.valueOf(temp[j]));
            }
            msg.put(Integer.valueOf(String.valueOf(temp[0])), tempArr);
        }
        return msg;
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
                NewStudents.setPreferredSize(new Dimension(1740, 250+270*iterForms));
                NewForm.setLocation(25, 225+250*(iterForms));
                newForms();
                NewStudents.add(mainPanel.get(iterForms));
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


    int ID_user = 0;
    //Метод отображения редактора объявлений
    void AddNewMess() throws IOException {
        NewMess = new JPanel();
        NewMess.setLayout(null);
        NewMess.setBackground(COLOR_FONT.Frame);
        NewMess.setSize(1740, 1080);
        NewMess.setLocation(180,0);
        add(NewMess);

        JLabel Title = new JLabel("РЕДАКТОР ОБЪЯВЛЕНИЙ");
        Title.setFont(new Font("Arial Black", Font.BOLD, 30));
        Title.setForeground(COLOR_FONT.Text);
        Title.setBounds(30, 50, 700, 60);
        NewMess.add(Title);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(30,140, 1650, 800);
        textArea.setFont(COLOR_FONT.simple);
        textArea.setBackground(COLOR_FONT.Text);
        textArea.setForeground(COLOR_FONT.Buttons);
        NewMess.add(textArea);

        StringBuilder str = new StringBuilder();
        URL url = null;
        url = new URL("https://buldakovn.pythonanywhere.com/getStudents");
        HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
        connection1.setDoInput(true);
        connection1.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection1.getInputStream()))) {
            for (String str2; (str2 = reader.readLine()) != null; ) {
                str.append(str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        msg = parseStudents(str);
        ArrayList<String> string; String[] FIO = new String[msg.size()];
        int i =0;
        for (Map.Entry<Integer, ArrayList<String>> e : msg.entrySet()) {
            string = e.getValue();
            FIO[i] = string.get(1) + " " + string.get(2);
            i++;
        }
        JComboBox box = new JComboBox(FIO);
        box.setBounds(30,110,200, 30);
        box.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        box.setForeground(new Color(117, 250, 114));
        box.setBackground(COLOR_FONT.TextArea);
        box.setFont(COLOR_FONT.simple);
        NewMess.add(box);
        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    ID_user = box.getSelectedIndex();
                }
            }
        });

        JButton button = new JButton("Отправить");
        button.setBounds(230, 110, 200, 30);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setForeground(new Color(117, 250, 114));
        button.setFocusPainted(false);
        button.setBackground(COLOR_FONT.TextArea);
        button.setFont(new Font("Arial Black", Font.BOLD, 20));
        NewMess.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (PythonInterpreter pyInterp = new PythonInterpreter()) {

                    pyInterp.exec("import requests");
                    pyInterp.exec("data = {\"Text\":\"" + textArea.getText() + "\", \"TargetId\":\"" + ID_user + "\", \"ToVk\":\"" + 1 + "\", \"ToTelegramm\":\"" + 1 + "\"}");
                    pyInterp.exec("a = requests.post(\"http://buldakovn.pythonanywhere.com/addMessage\", data)");

                }
            }
        });


    }
}

