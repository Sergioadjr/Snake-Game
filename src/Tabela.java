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
    private int contadorMaca =0;

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

    private int vidas = 5; 

    public Tabela() {

        tabela();
    }

    private void tabela() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT+100));
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
        Font smallFont = new Font("Helvetica", Font.BOLD, 17);
        g.setColor(Color.white);
        g.setFont(smallFont);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {
            g.drawString(vidas + " vidas", 50, 580);
            g.drawImage(apple, maca_x, maca_y, this);

            // CASO QUEIRA REDUZIR O CÓDIGO ---> for (int x = 2, y = 499 ; x< B_WIDTH &&
            // y>0; x+=17, y-=58 ) {

            // ALTERAÇÃO NAS PEDRAS DA COLUNA
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
            g.drawImage(pedra,x_pedra[56] = 110, y_pedra[56] = 70, this);
            g.drawImage(pedra,x_pedra[57] = 110, y_pedra[57] = 80, this);
            // ALTERAÇÃO NAS PEDRAS DA LINHA
            // } FIM <-----------

            for (int z = 0; z < tamanhoSnake; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(corpo, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            g.drawString(contadorMaca + " Maças", 150, 580);
            // MENSAGEM DO TEMPO
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Tente novamente";
        Font bigFont = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics metr = getFontMetrics(bigFont);

        g.setColor(Color.white);
        g.setFont(bigFont);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checarApple() {
        if ((x[0] == maca_x) && (y[0] == maca_y)) {
            tamanhoSnake++;
            localizacaoApple();
            contadorMaca++;
            if (contadorMaca==5) {
                vidas++;
                contadorMaca=0;
            }
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


    private void verificaVida(){
        if(vidas < 0){
            inGame = false;
        }
    }

    private void checarColisao() {

        for (int z = tamanhoSnake; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                reset();
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

        for (int i = 0; i < 58; i++) {
            if ((y[0] == y_pedra[i]) && (x[0] == x_pedra[i])) {
                reset();
            }
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void reset() {
        vidas--;
        verificaVida();
        contadorMaca=0;
        timer.stop();
        inicioGame();
        if (direcaoEsquerda) {
            direcaoEsquerda=false;
            direcaoDireita=true;
        }

    }

    private void localizacaoApple() {
        int r = (int) (Math.random() * RAND_POS);
        maca_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        maca_y = ((r * DOT_SIZE));
        checarMacaObstaculo();
    }

    private void checarMacaObstaculo(){
        for (int i = 0; i < 58 ; i++) {    
            if ((x_pedra [0] == maca_x) && (y_pedra [0] == maca_y)) {
                localizacaoApple();       
            }
        }
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