package fr.lteconsulting.activity;

/**
 * Represents a closing process, which means one or
 * more Activities destroyed in chain
 * 
 * @author Arnaud Tournier
 */
public interface IActivityClosingProcess
{
	/**
	 * Abort the closing process
	 */
	void abort();
}
