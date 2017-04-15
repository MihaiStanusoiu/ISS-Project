package interactor;

import domain.Author;
import java.util.List;

/**
 * Name:             AuthorInteractor
 * Effect:           Class for AuthorInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class AuthorInteractor implements InteractorInterface <Author>{

  @Override
  public boolean insert(Author obj) {
    return false;
  }

  @Override
  public boolean delete(Author obj) {
    return false;
  }

  @Override
  public boolean update(Author obj) {
    return false;
  }

  @Override
  public List<Author> selectAll() {
    return null;
  }

  @Override
  public Author selectById(Integer id) {
    return null;
  }
}
