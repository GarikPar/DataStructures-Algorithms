import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class SnakeGame extends JPanel implements KeyListener {
    public static final int WINDOW_SIZE = 800;
    private static final int PADDING = WINDOW_SIZE / 8;
    static int unit = 0;
    private int size = 20;
    private int time = 0;
    private int score = 0;
    private double generationProb = 0.025;
    private boolean isKeyPressed = false;
    private boolean isGameOver = false;
    private boolean isStopped = false;
    private boolean[][] grid;
    private char[][] fruits;
    private Snake snake;
    private Snake.Point dir;
    private JPanel stats = new JPanel(new FlowLayout());
    private JPanel options = new JPanel(new BorderLayout());
    private JLabel[] labels = new JLabel[2];
    private JButton save, load;
    private JTextField text;

    enum FruitType {
        APPLE('a', Color.RED, "A"),
        TANGERINE('t', Color.ORANGE, "T"),
        GRAPE('g', new Color(128, 0, 128), "G"),  // Purple
        WATERMELON('w', new Color(0, 100, 0), "W");  // Dark green

        private final char symbol;
        private final Color color;
        private final String display;

        FruitType(char symbol, Color color, String display) {
            this.symbol = symbol;
            this.color = color;
            this.display = display;
        }

        public char getSymbol() { return symbol; }
        public Color getColor() { return color; }
        public String getDisplay() { return display; }
    }

    private class Snake {
        ArrayList<Point> body;
        private Color color;

        Snake(int x, int y, int size) {
            body = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                body.add(new Point(x + i, y));
            }
            this.color = new Color(0, 128, 0);  // Snake green
            dir = new Point(1, 0);
        }

        void move(int x, int y) {
            if (!isStopped && !isGameOver) {
                Point newDir = new Point(x, y);
                if (newDir.x != -dir.x || newDir.y != -dir.y) {
                    dir = newDir;
                }
            }
        }

        boolean hit() {
            Point head = body.get(body.size() - 1);
            for (int i = 0; i < body.size() - 1; i++) {
                if (head.equals(body.get(i))) return true;
            }
            return false;
        }

        void updateGrid() {
            for (int i = 0; i < size; i++) {
                Arrays.fill(grid[i], false);
            }
            for (Point p : body) {
                if (p.x >= 0 && p.x < size && p.y >= 0 && p.y < size) {
                    grid[p.x][p.y] = true;
                }
            }
        }

        class Point {
            int x, y;
            Point(int x, int y) { this.x = x; this.y = y; }
            Point add(int i, int j, int w, int h) {
                int newX = (x + i) % w; if (newX < 0) newX += w;
                int newY = (y + j) % h; if (newY < 0) newY += h;
                return new Point(newX, newY);
            }
            Point diff(Point that) { return new Point(x - that.x, y - that.y); }
            void set(int x, int y) { this.x = x; this.y = y; }
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Point)) return false;
                Point p = (Point) o;
                return x == p.x && y == p.y;
            }
        }
    }

    public SnakeGame(String file) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setFocusable(true);

        // Initialize game state
        grid = new boolean[size][size];
        fruits = new char[size][size];
        for (char[] row : fruits) Arrays.fill(row, 'e');

        // UI Setup
        labels[0] = new JLabel("Score: " + score);
        labels[1] = new JLabel("Time: " + time);
        stats.add(labels[0]);
        stats.add(labels[1]);

        save = new JButton("Save");
        load = new JButton("Load");
        text = new JTextField(20);

        options.add(text, BorderLayout.CENTER);
        options.add(load, BorderLayout.WEST);
        options.add(save, BorderLayout.EAST);

        add(stats, BorderLayout.NORTH);
        add(options, BorderLayout.SOUTH);

        save.addActionListener(e -> save(text.getText()));
        load.addActionListener(e -> load(text.getText()));

        addKeyListener(this);

        // Initialize snake
        snake = new Snake(size/2, size/2, 3);
        dir = snake.new Point(1, 0);
    }

    void save(String file) {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(size);
            out.println(score);
            out.println(time);
            out.println(snake.body.size());
            for (Snake.Point p : snake.body) out.println(p.x + " " + p.y);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) out.print(fruits[i][j] + " ");
                out.println();
            }
            out.println(snake.color.getRed() + " " + snake.color.getGreen() + " " + snake.color.getBlue());
            out.println(dir.x + " " + dir.y);
        } catch (FileNotFoundException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    void load(String file) {
        try (Scanner sc = new Scanner(new File(file))) {
            size = sc.nextInt();
            score = sc.nextInt();
            time = sc.nextInt();
            grid = new boolean[size][size];
            fruits = new char[size][size];

            List<Snake.Point> points = new ArrayList<>();
            int snakeSize = sc.nextInt();
            for (int i = 0; i < snakeSize; i++) {
                points.add(snake.new Point(sc.nextInt(), sc.nextInt()));
            }

            snake = new Snake(0, 0, 1);
            snake.body = new ArrayList<>(points);

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    fruits[i][j] = sc.next().charAt(0);
                }
            }

            snake.color = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
            dir = snake.new Point(sc.nextInt(), sc.nextInt());

            labels[0].setText("Score: " + score);
            labels[1].setText("Time: " + time);
        } catch (Exception e) {
            System.err.println("Load failed, using default: " + e.getMessage());
            snake = new Snake(size/2, size/2, 3);
        }
    }

    public void drawGrid(Graphics g) {
        unit = (WINDOW_SIZE - 2 * PADDING) / size;
        snake.updateGrid();

        // Draw grid and fruits
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = PADDING + i * unit;
                int y = PADDING + j * unit;

                // Draw cell border
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(x, y, unit, unit);

                // Draw fruit if present
                char fruitChar = fruits[i][j];
                if (fruitChar != 'e') {
                    for (FruitType fruit : FruitType.values()) {
                        if (fruit.getSymbol() == fruitChar) {
                            // Draw fruit body
                            g.setColor(fruit.getColor());
                            g.fillOval(x + unit/4, y + unit/4, unit/2, unit/2);

                            // Draw fruit details
                            g.setColor(Color.WHITE);
                            g.setFont(new Font("Arial", Font.BOLD, unit/3));
                            g.drawString(fruit.getDisplay(),
                                    x + unit/2 - unit/6,
                                    y + unit/2 + unit/6);
                            break;
                        }
                    }
                }
            }
        }

        // Draw snake
        g.setColor(snake.color);
        for (int i = 0; i < snake.body.size(); i++) {
            Snake.Point p = snake.body.get(i);
            int x = PADDING + p.x * unit;
            int y = PADDING + p.y * unit;

            if (i == snake.body.size() - 1) { // Head
                g.fillOval(x, y, unit, unit);
                // Eyes
                g.setColor(Color.WHITE);
                int eyeSize = unit/4;
                if (dir.x == 1) { // Right
                    g.fillOval(x + unit/2, y + unit/4, eyeSize, eyeSize);
                    g.fillOval(x + unit/2, y + 3*unit/4, eyeSize, eyeSize);
                } else if (dir.x == -1) { // Left
                    g.fillOval(x + unit/4, y + unit/4, eyeSize, eyeSize);
                    g.fillOval(x + unit/4, y + 3*unit/4, eyeSize, eyeSize);
                } else if (dir.y == 1) { // Down
                    g.fillOval(x + unit/4, y + unit/2, eyeSize, eyeSize);
                    g.fillOval(x + 3*unit/4, y + unit/2, eyeSize, eyeSize);
                } else { // Up
                    g.fillOval(x + unit/4, y + unit/4, eyeSize, eyeSize);
                    g.fillOval(x + 3*unit/4, y + unit/4, eyeSize, eyeSize);
                }
                g.setColor(snake.color);
            } else if (i == 0) { // Tail
                int[] xPoints, yPoints;
                if (snake.body.size() > 1) {
                    Snake.Point next = snake.body.get(1);
                    if (next.x > p.x) { // Right
                        xPoints = new int[]{x, x + unit, x + unit};
                        yPoints = new int[]{y + unit/2, y, y + unit};
                    } else if (next.x < p.x) { // Left
                        xPoints = new int[]{x + unit, x, x};
                        yPoints = new int[]{y + unit/2, y, y + unit};
                    } else if (next.y > p.y) { // Down
                        xPoints = new int[]{x + unit/2, x, x + unit};
                        yPoints = new int[]{y, y + unit, y + unit};
                    } else { // Up
                        xPoints = new int[]{x + unit/2, x, x + unit};
                        yPoints = new int[]{y + unit, y, y};
                    }
                    g.fillPolygon(xPoints, yPoints, 3);
                }
            } else { // Body
                g.fillRect(x, y, unit, unit);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (!isGameOver && !isStopped) {
            // Generate fruits
            if (Math.random() < generationProb) {
                int x, y;
                do {
                    x = new Random().nextInt(size);
                    y = new Random().nextInt(size);
                } while (grid[x][y]);

                FruitType randomFruit = FruitType.values()[
                        new Random().nextInt(FruitType.values().length)];
                fruits[x][y] = randomFruit.getSymbol();
            }

            // Update game state
            time++;
            labels[1].setText("Time: " + time);

            // Move snake
            Snake.Point head = snake.body.get(snake.body.size() - 1);
            Snake.Point newHead = head.add(dir.x, dir.y, size, size);

            if (grid[newHead.x][newHead.y] && !newHead.equals(snake.body.get(snake.body.size() - 2))) {
                isGameOver = true;
            } else {
                char fruit = fruits[newHead.x][newHead.y];
                if (fruit != 'e') {
                    score++;
                    labels[0].setText("Score: " + score);
                    fruits[newHead.x][newHead.y] = 'e';
                    snake.body.add(newHead);
                } else {
                    snake.body.remove(0);
                    snake.body.add(newHead);
                }
            }
        }

        drawGrid(g);

        try { Thread.sleep(150); } catch (InterruptedException e) {}
        repaint();

        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", WINDOW_SIZE/2 - 150, WINDOW_SIZE/2);
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) { isKeyPressed = false; }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: if (dir.y == 0) snake.move(0, -1); break;
            case KeyEvent.VK_DOWN: if (dir.y == 0) snake.move(0, 1); break;
            case KeyEvent.VK_LEFT: if (dir.x == 0) snake.move(-1, 0); break;
            case KeyEvent.VK_RIGHT: if (dir.x == 0) snake.move(1, 0); break;
            case KeyEvent.VK_S: isStopped = !isStopped; break;
            case KeyEvent.VK_R: snake.color = new Color(
                    new Random().nextInt(256),
                    new Random().nextInt(256),
                    new Random().nextInt(256));
                break;
        }
        isKeyPressed = true;
    }
}