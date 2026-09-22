// Abstract base class with static auto-incrementing ID[cite: 16]
abstract class ArtPiece {
    private static int counter = 100;
    private final String pieceId;
    protected String title;

    public ArtPiece(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        counter++;
        this.pieceId = "ART-" + counter;
        this.title = title.trim();
    }

    public String getPieceId() {
        return this.pieceId;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + this.title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + this.title + ", carved from stone";
    }
}

public class GalleryCardsApp {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}