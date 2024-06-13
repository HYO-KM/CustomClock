import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 時計機能の設定プログラム
 * @version 3.0.0
 */
public class SettingClock extends Object{

    CustomClock CC = new CustomClock();

    /** 
     * @param setselectTime 指定時間表示形式
     * @param setselectColor 指定カラー
     * @param setselectFont 指定フォント
     */

    public void setPerform(String setselectTime ,String setselectColor ,String setselectFont) {
        
        //設定ウィンドウの準備
        JFrame setFrame = new JFrame("設定");
        setFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setFrame.setSize(400,240);
        setFrame.setLayout(new GridLayout(5,2));

        //フォント変更ラベル・ボタンの作成
        JLabel fontChangeLabel = new JLabel();
        fontChangeLabel.setText("フォントを変更する");
        
        
        //日付+時刻、時刻のみ、日付のみ 変更ラベルの作成
        JLabel timeStyleChangeLabel = new JLabel();
        timeStyleChangeLabel.setText("日付+時刻、時刻のみ、日付のみ ");
        

        //背景変更ラベルの作成
        JLabel backGroundLabel = new JLabel();
        backGroundLabel.setText("背景を変更する");
        

        //文字の色　変更ラベルの作成
        JLabel colorChangeLabel = new JLabel();
        colorChangeLabel.setText("文字の色を変更する");
        

        //設定ウィンドウの空白部分
        JLabel blankLabel = new JLabel();
        blankLabel.setText("");
        

        //確定ボタンの作成
        JButton confirmButton = new JButton("確定");
        

        //フォントコンボボタンの作成・フォント設定
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontStyles = ge.getAvailableFontFamilyNames();
        @SuppressWarnings("rawtypes")
        JComboBox fontCombo = new JComboBox<>(fontStyles);
        fontCombo.setSelectedItem(setselectFont);
        
        
        
        //時間表示のコンボボタンの作成・時間表示設定
        String[] timeStyles = {"yyyy/MM/dd HH:mm:ss","HH:mm:ss","yyyy/MM/dd" };
        @SuppressWarnings("rawtypes")
        JComboBox timeCombo = new JComboBox<>(timeStyles);
        timeCombo.setSelectedItem(setselectTime);
        
        

        //文字の色変更コンボボタンの作成・文字の色設定
        String[] colors = {"BLACK","WHITE","RED","YELLOW","GREEN","BLUE","ORANGE" };
        @SuppressWarnings("rawtypes")
        JComboBox colorCombo = new JComboBox<>(colors);
        colorCombo.setSelectedItem(setselectColor);
        
        

        //背景画像のファイル　処理
        JButton fileButton = new JButton("画像選択");
        fileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent fileEvent) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(fileChooser);
                if(result == JFileChooser.APPROVE_OPTION) {
                    
                    File selectedFile = fileChooser.getSelectedFile();
                    CC.selectFile = selectedFile.toString();
                }
            }
        });


        //ラベルやボタンの追加
        setFrame.add(fontChangeLabel);
        setFrame.add(fontCombo);

        setFrame.add(timeStyleChangeLabel);
        setFrame.add(timeCombo);

        setFrame.add(backGroundLabel);
        setFrame.add(fileButton);

        setFrame.add(colorChangeLabel);
        setFrame.add(colorCombo);

        setFrame.add(blankLabel);
        setFrame.add(confirmButton);

        
        
        //fontComboのアクションリスナーを追加
        fontCombo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent fontTap) {

                // 選択されたアイテムを取得
                String setselectFont = (String) fontCombo.getSelectedItem();
                CC.selectFont = setselectFont;
                
            }
        });

        //timeComboのアクションリスナーを追加
        timeCombo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent timeTap) {
                // 選択されたアイテムを取得
                String setselectTime = (String) timeCombo.getSelectedItem();
                CC.selectTime = setselectTime;
                
            }
        });

        //colorComboのアクションリスナーを追加
        colorCombo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent colorTap) {
                // 選択されたアイテムを取得
                String setselectColor = (String) colorCombo.getSelectedItem();
                CC.selectColor = setselectColor;

            }
        });

        

        //確定ボタンの処理
        confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent confirmEvent) {

                setFrame.dispose();
                CC.perform();
                
            }
        });

        //設定ウィンドウを表示
        setFrame.setLocationByPlatform(true);
        setFrame.setVisible(true);



    }

}
