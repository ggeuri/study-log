public interface PersistableRepo extends Repo{


    public void saveToFile();
    public void loadFromFile(); 


    //  if (repo instanceof FileRepo p) p.loadFromFile();
    //  if (repo instanceof FileRepo p) p.saveToFile();

}
