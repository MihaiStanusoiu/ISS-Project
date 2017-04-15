package interactor;

import java.util.List;

/**
 * Name:             InteractorInterface
 * Effect:           General interface for interactors
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public interface InteractorInterface<T>{
  /**
   * Effect: Returns a bool representing the success or failure of the insert operation.
   * @return [boolean] : returns success/failure.
   */
  public Integer insert(T obj) throws Exception;

  /**
   * Effect: Returns a bool representing the success or failure of the delete operation.
   * @return [boolean] : returns success/failure.
   */
  public T delete(T obj) throws Exception;

  /**
   * Effect: Returns a bool representing the success or failure of the update operation.
   * @return [boolean] : returns success/failure.
   */
  public void update(T obj) throws Exception;

  /**
   * Effect: Returns a list of all objects of type T.
   * @return [List of T] : returns a list of T elements.
   */
  public List<T> selectAll();

  /**
   * Effect: Returns an element of type T that has the identifier id.
   * @return [T] : returns T element.
   */
  public T selectById(Integer id) throws Exception;
}
