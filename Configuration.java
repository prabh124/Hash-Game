public class Configuration 
{
	//attributes
	String config;
	int score;
	
	//creates a config object which stores the game config, and a score object which keeps score
	public Configuration(String config, int score)
	{
		this.config = config;
		this.score = score;
	}
	//returns the string configuration of the data
	public String getStringConfiguration()
	{
		return config;
	}
	//returns the score of the config
	public int getScore()
	{
		return score;
	}
}