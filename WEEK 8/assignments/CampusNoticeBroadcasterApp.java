import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    String getChannelName();
    void send(String recipientName, String message);
}

class EmailChannel implements NotificationChannel {
    @Override public String getChannelName() { return "Email"; }
    @Override public void send(String recipientName, String message) {
        System.out.printf("[Email -> %s] %s\n", recipientName, message);
    }
}

class SmsChannel implements NotificationChannel {
    @Override public String getChannelName() { return "SMS"; }
    @Override public void send(String recipientName, String message) {
        System.out.printf("[SMS -> %s] %s\n", recipientName, message);
    }
}

class AppChannel implements NotificationChannel {
    @Override public String getChannelName() { return "App"; }
    @Override public void send(String recipientName, String message) {
        System.out.printf("[App -> %s] %s\n", recipientName, message);
    }
}

class CampusStudent {
    private final String name;
    private final String department;
    private final List<NotificationChannel> channels;

    public CampusStudent(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = channels;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getChannels() { return channels; }
}

class CampusNotice {
    private final String title;
    private final List<String> targetDepartments;

    public CampusNotice(String title, List<String> targetDepartments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (targetDepartments == null || targetDepartments.isEmpty()) {
            throw new IllegalArgumentException("At least one target department is required."); //[cite: 73]
        }
        this.title = title.trim();
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class CampusNoticeBoard {
    private final List<CampusStudent> directory = new ArrayList<>();

    public void registerStudent(CampusStudent s) {
        directory.add(s);
    }

    public void postNotice(String title, List<String> departments) {
        CampusNotice notice;
        try {
            notice = new CampusNotice(title, departments);
        } catch (IllegalArgumentException e) {
            System.out.printf("Cannot post notice: %s\n", e.getMessage());
            return;
        }

        System.out.printf("Notice '%s' posted to %s.\n",
                notice.getTitle(), String.join(", ", notice.getTargetDepartments()));

        for (CampusStudent student : directory) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcasterApp {
    public static void main(String[] args) {
        CampusNoticeBoard board = new CampusNoticeBoard();

        // Asha (CSE) prefers Email and App[cite: 73]
        CampusStudent asha = new CampusStudent("Asha", "CSE",
                Arrays.asList(new EmailChannel(), new AppChannel()));

        // Ravi (ECE) prefers SMS[cite: 73]
        CampusStudent ravi = new CampusStudent("Ravi", "ECE",
                Arrays.asList(new SmsChannel()));

        board.registerStudent(asha);
        board.registerStudent(ravi);

        // Admin posts notice for CSE[cite: 73]
        board.postNotice("Lab Closed Tomorrow", Arrays.asList("CSE"));

        // Admin posts notice for CSE and ECE[cite: 73]
        board.postNotice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));

        // Admin attempts posting with no target department[cite: 73]
        board.postNotice("Sports Day", new ArrayList<>());
    }
}