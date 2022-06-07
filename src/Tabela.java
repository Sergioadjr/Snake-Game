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
    private final int DELAY = 100;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int x_pedra[] = new int[ALL_DOTS];
    private int y_pedra[] = new int[ALL_DOTS];
    private int quantidadePedras = 0;

    private int tamanhoSnake;
    private int maca_x;
    private int maca_y;
    private int contadorMaca = 0;
    private int contadorCronometro = 120000;


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

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT + 100));
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
        localizacaoPedra();

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
            g.drawString(vidas + " vidas", 50, 540);
            g.drawImage(apple, maca_x, maca_y, this);

            for (int i = 0; i < quantidadePedras; i++) {
                g.drawImage(pedra, x_pedra[i], y_pedra[i], this);
            }

            for (int z = 0; z < tamanhoSnake; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(corpo, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            g.drawString(contadorMaca + " Maças", 150, 540);
            g.drawString("Tempo: " + ((contadorCronometro/1000))/60 + ":"+ ((contadorCronometro/1000)%60), 360,540);
            
            Font smallFont2 = new Font ("Helvetica", Font.BOLD, 13);
            g.setFont(smallFont2);
            g.drawString("Acadêmicos: Anderson; Gabriel; João Pedro; Lenícia e Sérgio",50,580);
            

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
            if (contadorMaca == 3) {
                vidas++;
                contadorMaca = 0;
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
        if (contadorCronometro >=0 ) {
                contadorCronometro -=100;
            }else {
                inGame=false;
            }
    }

    private void verificaVida() {
        if (vidas < 0) {
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
        contadorMaca = 0;
        timer.stop();
        inicioGame();
        if (direcaoEsquerda) {
            direcaoEsquerda = false;
            direcaoDireita = true;
        }

    }

    private String pedraOuNao() {
        int r = (int) (Math.random() * 100);
        if (r <= 1) {
            return "pedra";
        }
        return "nao";
    }

    private void localizacaoPedra() {
        for (int x = 0; x < B_WIDTH; x += 10) {
            for (int y = 0; y < B_HEIGHT; y += 10){
                if (pedraOuNao() == "pedra") {
                    if (quantidadePedras >= 10) {
                        break;
                    }
                    x_pedra[quantidadePedras] = x;
                    y_pedra[quantidadePedras] = y;
                    quantidadePedras++;
                }
            }
        }
    }

    private void localizacaoApple() {
        int r = (int) (Math.random() * RAND_POS);
        maca_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        maca_y = ((r * DOT_SIZE));
        checarMacaObstaculo();
    }

    private void checarMacaObstaculo() {
        for (int i = 0; i < 58; i++) {
            if ((x_pedra[0] == maca_x) && (y_pedra[0] == maca_y)) {
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