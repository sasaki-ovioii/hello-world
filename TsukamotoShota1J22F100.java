import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TsukamotoShota1J22F100 extends JFrame {
    private Point mousePoint = null; //掴んだ位置を一時保存する変数

    public TsukamotoShota1J22F100() {
        //ウィンドウ設定
        setTitle("カニ炒飯");
        setUndecorated(true); //ウィンドウの枠を非表示
        setBackground(new Color(0, 0, 0, 0)); //ウインドウ背景を透明化
        setAlwaysOnTop(true); //ウインドウを常に手前に
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //終了設定(おまじない)

        Container c = getContentPane(); //配置場所を取得
        c.setLayout(new FlowLayout()); //中央並べ

        ImageIcon kanichaOrigin = new ImageIcon("kanicha-_dot.png"); //画像の読み込み
        Image kanicha = kanichaOrigin.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); //サイズ調整しつつ見た目を滑らかに調整
        JLabel kanichaLabel = new JLabel(new ImageIcon(kanicha));   //Labelに貼るためにIcon化
        c.add(kanichaLabel); //ウインドウに追加

        //マウスで移動できるようにするための処理
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //クリックされた場所を覚える
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mousePoint = e.getPoint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                //指を離したら覚えた位置をリセットする
                mousePoint = null;
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                //マウスを動かしている間、ウインドウがマウスに追従
                if (mousePoint != null) {
                    Point now = e.getLocationOnScreen();
                    setLocation(now.x - mousePoint.x, now.y - mousePoint.y);
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { //もしダブルクリックされたら、の意
                    System.exit(0); //プログラム終了
                }
            }
        };
        //作成したマウス操作のルールをLabelに
        kanichaLabel.addMouseListener(adapter);
        kanichaLabel.addMouseMotionListener(adapter);

        pack(); //画像サイズに合わせてウインドウを自動調整
        setLocation(500, 400); //初期位置を設定
    }

    void main() {
        TsukamotoShota1J22F100 t = new TsukamotoShota1J22F100();
        t.setVisible(true);
    }
}