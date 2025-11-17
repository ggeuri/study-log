public class App {
    public static void main(String[] args) throws Exception {
        Repo repo = new FileRepo(); 
        if (repo instanceof PersistableRepo p) p.loadFromFile();

        Control control = new Control(repo);

        try {
            control.run();
        } finally  {
            if (repo instanceof PersistableRepo p) p.saveToFile();
        }


        
    }
}
