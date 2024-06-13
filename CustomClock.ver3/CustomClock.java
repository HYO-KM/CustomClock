import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 時計表示プログラム
 * @version 3.0.0
 */

public class CustomClock extends Object {

    String selectTime = "yyyy/MM/dd HH:mm:ss";
    String selectFont = "Consolas";
    String selectColor = "BLACK";
    String selectFile = "";
    BufferedImage backgroundImage;
    Color csc;
    

    public void perform() {

        //メインウィンドウの準備
        JFrame mainFrame = new JFrame("CustomClock");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(480,270);
        mainFrame.setLayout(new GridBagLayout());
        
        
        //ファイルの存在確認
        if(selectFile != ""){


            //画像ファイルを読み込み
            try {

            backgroundImage = ImageIO.read(new File(selectFile));


            } catch (IOException e) {
                System.out.println("エラー");
            }
        }

        //パネルの設定
        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(backgroundImage != null){
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);
                }
            }
        };
        panel.setLayout(new GridBagLayout());

        //メインフレームにパネルを追加
        mainFrame.setContentPane(panel);

        
        //文字の色(STRING型→Color型)
        switch (selectColor) {
            case "BLACK":
                csc = Color.BLACK;
                break;
            
            case "WHITE":
                csc = Color.WHITE;
                break;

            case "RED":
                csc = Color.RED;
                break;

            case "YELLOW":
                csc = Color.YELLOW;
                break;

            case "GREEN":
                csc = Color.GREEN;
                break;

            case "BLUE":
                csc = Color.BLUE;
                break;

            case "ORANGE":
                csc = Color.ORANGE;
                break;

            default:
                break;
        }
        

        //現在日付・時刻　取得
        LocalDateTime nowDateTime = LocalDateTime.now();

        

        //日付・時刻ラベルの追加
        JLabel datetimeLabel = new JLabel();

        //表示形式を適応
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern(selectTime);  
        String stringDateTime = DTF.format(nowDateTime);
        datetimeLabel.setText(stringDateTime);
        datetimeLabel.setFont(new Font(selectFont,Font.PLAIN,40));
        datetimeLabel.setForeground(csc);
        

        //設定ボタンの追加
        JButton settingButton = new JButton("S");
        settingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent anEvent) {
                SettingClock set = new SettingClock();
                set.setPerform(selectTime,selectColor,selectFont);

            }
        });
        
        
    
        //設定ボタンの表示
        settingButton.setPreferredSize(new Dimension(25,25));
        settingButton.setMargin(new Insets(0,0,0,0));
        
        


         // タイマーを作成し、1秒ごとに時計を更新
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 現在の日時を取得
                LocalDateTime nowDateTime = LocalDateTime.now();
                // 日時を指定されたフォーマットに変換
                String formattedDate = DTF.format(nowDateTime);
                // ラベルに日時を設定
                datetimeLabel.setText(formattedDate.toString());
                
            }
        });
        timer.start(); // タイマーを開始
        
        
        //パネルに追加
        panel.add(datetimeLabel);
        panel.add(settingButton);

        //メインウィンドウの表示
        mainFrame.setLocationByPlatform(true);
        mainFrame.setVisible(true);

        

        
    }
}


