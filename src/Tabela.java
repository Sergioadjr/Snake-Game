import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tabela extends JPanel implements ActionListener {

    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 1000;
    private final int RAND_POS = 29;
    private final int DELAY = 34;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int x_pedra[] = new int[ALL_DOTS];
    private int y_pedra[] = new int[ALL_DOTS];

    private int tamanhoSnake;
    private int maca_x;
    private int maca_y;

    private boolean direcaoEsquerda = false;
    private boolean direcaoDireita = true;
    private boolean direcaoCima = false;
    private boolean direcaoBaixo = false;
    private boolean inGame = true;

    private Timer timer;
    private Image corpo;
    private Image apple;
    private Image head;
    private Image pedra;

    public Tabela() {

        tabela();
    }

    private void tabela() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        Imagens();
        inicioGame();
    }

    private void Imagens() {

        ImageIcon corpoImg = new ImageIcon("dot.png");
        corpo = corpoImg.getImage();

        ImageIcon masan = new ImageIcon("apple.png");
        apple = masan.getImage();

        ImageIcon cabe = new ImageIcon("head.png");
        head = cabe.getImage();

        ImageIcon obstaculo = new ImageIcon("pedra.png");
        pedra = obstaculo.getImage();
    }

    private void inicioGame() {

        tamanhoSnake = 10;

        for (int z = 0; z < tamanhoSnake; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        localizacaoApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, maca_x, maca_y, this);

            // CASO QUEIRA REDUZIR O CÓDIGO ---> for (int x = 2, y = 499 ; x< B_WIDTH &&
            // y>0; x+=17, y-=58 ) {

            // ALTERAÇÃO NAS PEDRAS DA COLUNA
            g.drawImage(pedra, x_pedra[0] = 110, y_pedra[0] = 70, this);
            g.drawImage(pedra,x_pedra[0] = 110, y_pedra[0] = 80, this);
            g.drawImage(pedra,x_pedra[0] = 110, y_pedra[0] = 90, this);
            g.drawImage(pedra, x_pedra[1] = 110, y_pedra[1] = 100, this);
            g.drawImage(pedra, x_pedra[2] = 110, y_pedra[2] = 110, this);
            g.drawImage(pedra, x_pedra[3] = 110, y_pedra[3] = 120, this);
            g.drawImage(pedra, x_pedra[4] = 110, y_pedra[4] = 130, this);
            g.drawImage(pedra, x_pedra[5] = 110, y_pedra[5] = 140, this);
            g.drawImage(pedra,x_pedra[6] = 110, y_pedra[6] = 150, this);
            g.drawImage(pedra,x_pedra[7] = 110, y_pedra[7] = 240, this);
            g.drawImage(pedra,x_pedra[8] = 110, y_pedra[8] = 250, this);
            g.drawImage(pedra,x_pedra[9] = 110, y_pedra[9] = 260, this);
            g.drawImage(pedra,x_pedra[10] = 110, y_pedra[10] = 270, this);
            g.drawImage(pedra,x_pedra[11] = 110, y_pedra[11] = 280, this);
            g.drawImage(pedra,x_pedra[12] = 110, y_pedra[12] = 290, this);
            g.drawImage(pedra,x_pedra[13] = 110, y_pedra[13] = 300, this);
            g.drawImage(pedra,x_pedra[14] = 110, y_pedra[14] = 310, this);
            g.drawImage(pedra,x_pedra[15] = 110, y_pedra[15] = 320, this);
            g.drawImage(pedra,x_pedra[16] = 110, y_pedra[16] = 380, this);
            g.drawImage(pedra,x_pedra[17] = 110, y_pedra[17] = 390, this);
            g.drawImage(pedra,x_pedra[18] = 110, y_pedra[18] = 400, this);
            g.drawImage(pedra,x_pedra[19] = 110, y_pedra[19] = 410, this);
            g.drawImage(pedra,x_pedra[20] = 110, y_pedra[20] = 420, this);
            g.drawImage(pedra,x_pedra[21] = 110, y_pedra[21] = 430, this);
            g.drawImage(pedra,x_pedra[22] = 110, y_pedra[22] = 440, this);
            g.drawImage(pedra,x_pedra[23] = 110, y_pedra[23] = 450, this);
            g.drawImage(pedra,x_pedra[24] = 110, y_pedra[24] = 460, this);
            g.drawImage(pedra,x_pedra[25] = 110, y_pedra[25] = 470, this);
            g.drawImage(pedra, x_pedra[26] = 360, y_pedra[26] = 160, this);
            g.drawImage(pedra, x_pedra[27] = 360, y_pedra[27] = 60, this);
            g.drawImage(pedra, x_pedra[28] = 360, y_pedra[28] = 70, this);
            g.drawImage(pedra,x_pedra[29] = 360, y_pedra[29] = 80, this);
            g.drawImage(pedra,x_pedra[30] = 360, y_pedra[30] = 90, this);
            g.drawImage(pedra, x_pedra[31] = 360, y_pedra[31] = 100, this);
            g.drawImage(pedra, x_pedra[32] = 360, y_pedra[32] = 110, this);
            g.drawImage(pedra, x_pedra[33] = 360, y_pedra[33] = 120, this);
            g.drawImage(pedra, x_pedra[34] = 360, y_pedra[34] = 130, this);
            g.drawImage(pedra, x_pedra[35] = 360, y_pedra[35] = 140, this);
            g.drawImage(pedra,x_pedra[36] = 360, y_pedra[36] = 150, this);
            g.drawImage(pedra,x_pedra[37] = 360, y_pedra[37] = 240, this);
            g.drawImage(pedra,x_pedra[38] = 360, y_pedra[38] = 250, this);
            g.drawImage(pedra,x_pedra[39] = 360, y_pedra[39] = 260, this);
            g.drawImage(pedra,x_pedra[40] = 360, y_pedra[40] = 270, this);
            g.drawImage(pedra,x_pedra[41] = 360, y_pedra[41] = 280, this);
            g.drawImage(pedra,x_pedra[42] = 360, y_pedra[42] = 290, this);
            g.drawImage(pedra,x_pedra[43] = 360, y_pedra[43] = 300, this);
            g.drawImage(pedra,x_pedra[44] = 360, y_pedra[44] = 310, this);
            g.drawImage(pedra,x_pedra[45] = 360, y_pedra[45] = 320, this);
            g.drawImage(pedra,x_pedra[46] = 360, y_pedra[46] = 380, this);
            g.drawImage(pedra,x_pedra[47] = 360, y_pedra[47] = 390, this);
            g.drawImage(pedra,x_pedra[48] = 360, y_pedra[48] = 400, this);
            g.drawImage(pedra,x_pedra[49] = 360, y_pedra[49] = 410, this);
            g.drawImage(pedra,x_pedra[50] = 360, y_pedra[50] = 420, this);
            g.drawImage(pedra,x_pedra[51] = 360, y_pedra[51] = 430, this);
            g.drawImage(pedra,x_pedra[52] = 360, y_pedra[52] = 440, this);
            g.drawImage(pedra,x_pedra[53] = 360, y_pedra[53] = 450, this);
            g.drawImage(pedra,x_pedra[54] = 360, y_pedra[54] = 460, this);
            g.drawImage(pedra,x_pedra[55] = 360, y_pedra[55] = 470, this);

            // ALTERAÇÃO NAS PEDRAS DA LINHA
            g.drawImage(pedra, x_pedra[56] = 70, y_pedra[56] = 100, this);
            g.drawImage(pedra,x_pedra[57] = 80, y_pedra[57] = 100, this);
            g.drawImage(pedra,x_pedra[58] = 90, y_pedra[58] = 100, this);
            g.drawImage(pedra, x_pedra[59] = 100, y_pedra[59] = 100, this);
            g.drawImage(pedra, x_pedra[60] = 110, y_pedra[60] = 100, this);
            g.drawImage(pedra, x_pedra[61] = 120, y_pedra[61] = 100, this);
            g.drawImage(pedra, x_pedra[2] = 130, y_pedra[62] = 100, this);
            g.drawImage(pedra, x_pedra[63] = 140, y_pedra[63] = 100, this);
            g.drawImage(pedra,x_pedra[64] = 150, y_pedra[64] = 100, this);
            g.drawImage(pedra,x_pedra[65] = 210, y_pedra[65] = 100, this);
            g.drawImage(pedra,x_pedra[66] = 220, y_pedra[66] = 100, this);
            g.drawImage(pedra,x_pedra[67] = 230, y_pedra[67] = 100, this);
            g.drawImage(pedra,x_pedra[68] = 240, y_pedra[68] = 100, this);
            g.drawImage(pedra,x_pedra[69] = 250, y_pedra[69] = 100, this);
            g.drawImage(pedra,x_pedra[70] = 290, y_pedra[70] = 100, this);
            g.drawImage(pedra,x_pedra[71] = 300, y_pedra[71] = 100, this);
            g.drawImage(pedra,x_pedra[72] = 310, y_pedra[72] = 100, this);
            g.drawImage(pedra,x_pedra[73] = 320, y_pedra[73] = 100, this);
            g.drawImage(pedra,x_pedra[74] = 330, y_pedra[74] = 100, this);
            g.drawImage(pedra,x_pedra[75] = 340, y_pedra[75] = 100, this);
            g.drawImage(pedra,x_pedra[76] = 350, y_pedra[76] = 100, this);
            g.drawImage(pedra,x_pedra[77] = 360, y_pedra[77] = 100, this);
            g.drawImage(pedra,x_pedra[78] = 370, y_pedra[78] = 100, this);
            g.drawImage(pedra,x_pedra[79] = 380, y_pedra[79] = 100, this);
            g.drawImage(pedra,x_pedra[80] = 410, y_pedra[80] = 100, this);
            g.drawImage(pedra, x_pedra[81] = 420, y_pedra[81] = 100, this);
            g.drawImage(pedra,x_pedra[82] = 430, y_pedra[82] = 100, this);
            g.drawImage(pedra,x_pedra[83] = 440, y_pedra[83] = 100, this);
            g.drawImage(pedra, x_pedra[84] = 450, y_pedra[84] = 100, this);
            g.drawImage(pedra, x_pedra[85] = 460, y_pedra[85] = 100, this);
            g.drawImage(pedra, x_pedra[86] = 470, y_pedra[86] = 100, this);
            g.drawImage(pedra, x_pedra[87] = 480, y_pedra[87] = 100, this);

            g.drawImage(pedra, x_pedra[88] = 260, y_pedra[88] = 350, this);
            g.drawImage(pedra,x_pedra[89] = 270, y_pedra[89] = 350, this);
            g.drawImage(pedra,x_pedra[90] = 330, y_pedra[90] = 350, this);
            g.drawImage(pedra,x_pedra[91] = 340, y_pedra[91] = 350, this);
            g.drawImage(pedra,x_pedra[92] = 350, y_pedra[92] = 350, this);
            g.drawImage(pedra,x_pedra[93] = 360, y_pedra[93] = 350, this);
            g.drawImage(pedra,x_pedra[94] = 370, y_pedra[94] = 350, this);
            g.drawImage(pedra,x_pedra[95] = 380, y_pedra[95] = 350, this);
            g.drawImage(pedra,x_pedra[96] = 390, y_pedra[96] = 350, this);
            g.drawImage(pedra,x_pedra[97] = 400, y_pedra[97] = 350, this);
            g.drawImage(pedra,x_pedra[98] = 410, y_pedra[98] = 350, this);
            g.drawImage(pedra,x_pedra[99] = 210, y_pedra[99] = 350, this);
            g.drawImage(pedra,x_pedra[100] = 220, y_pedra[100] = 350, this);
            g.drawImage(pedra,x_pedra[101] = 450, y_pedra[101] = 350, this);
            g.drawImage(pedra,x_pedra[102] = 460, y_pedra[102] = 350, this);
            g.drawImage(pedra,x_pedra[103] = 470, y_pedra[103] = 350, this);
            g.drawImage(pedra,x_pedra[104] = 480, y_pedra[104] = 350, this);
            g.drawImage(pedra,x_pedra[105] = 490, y_pedra[105] = 350, this);

            // } FIM <-----------

            for (int z = 0; z < tamanhoSnake; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(corpo, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Tente novamente";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checarApple() {

        if ((x[0] == maca_x) && (y[0] == maca_y)) {

            tamanhoSnake++;
            localizacaoApple();
        }
    }

    private void movimentar() {

        for (int z = tamanhoSnake; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (direcaoEsquerda) {
            x[0] -= DOT_SIZE;
        }

        if (direcaoDireita) {
            x[0] += DOT_SIZE;
        }

        if (direcaoCima) {
            y[0] -= DOT_SIZE;
        }

        if (direcaoBaixo) {
            y[0] += DOT_SIZE;
        }
    }

    private void checarColisao() {

        for (int z = tamanhoSnake; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            y[0] = y[500];
        }

        if (y[0] < 0) {
            y[0] = B_HEIGHT;
        }

        if (x[0] >= B_WIDTH) {
            x[0] = x[500];
        }

        if (x[0] < 0) {
            x[0] = B_WIDTH;
        }

        for (int i = 0; i < 105; i++) {
            if ((y[0] == y_pedra[i]) && (x[0] == x_pedra[i])) {
                inGame = false;
            }
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void localizacaoApple() {

        int r = (int) (Math.random() * RAND_POS);
        maca_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        maca_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checarApple();
            checarColisao();
            movimentar();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!direcaoDireita)) {
                direcaoEsquerda = true;
                direcaoCima = false;
                direcaoBaixo = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!direcaoEsquerda)) {
                direcaoDireita = true;
                direcaoCima = false;
                direcaoBaixo = false;
            }

            if ((key == KeyEvent.VK_UP) && (!direcaoBaixo)) {
                direcaoCima = true;
                direcaoDireita = false;
                direcaoEsquerda = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!direcaoCima)) {
                direcaoBaixo = true;
                direcaoDireita = false;
                direcaoEsquerda = false;
            }
        }
    }
}