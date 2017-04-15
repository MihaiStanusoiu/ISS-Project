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
  public Integer insert(Author obj) throws Exception {
    return null;
  }

  @Override
  public Author delete(Author obj) throws Exception {
    return null;
  }

  @Override
  public void update(Author objInit, Author objUpd) throws Exception {
  }

  @Override
  public List<Author> selectAll() {
    return null;
  }

  @Override
  public Author selectById(Integer id) throws Exception {
    return null;
  }
}
