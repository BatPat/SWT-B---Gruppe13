import java.util.List;

public interface TheoriestundeDao {
	
	public List<Theoriestunde> getAlleTheoriestunden();
	
	public void addTheoriestunde(Theoriestunde theoriestunde);
	
	public void updateTheoriestunde(Theoriestunde theoriestunde);
	
	public void deleteTheoriestunde(Theoriestunde theoriestunde);
}
