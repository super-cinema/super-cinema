//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import UIKit

final class MoviesListViewController: UIViewController {

    @IBOutlet weak private var tableView: UITableView!

    private var task: URLSessionTask?
    private var movies: [Movie] = [] {
        didSet {
            DispatchQueue.main.async { [weak self] in
                self?.tableView.reloadData()
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.dataSource = self

        let addMoviewItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(presentAddMovie))
        navigationItem.rightBarButtonItem = addMoviewItem

        title = "MoviesList"
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        loadMovies()
    }

    private func loadMovies() {
        Api().getMovies { [weak self] result in
            self?.handleResult(result)
        }
    }

    private func handleResult(_ result: Result<Any>) {
        switch result {
        case .error(let error):
            print(error)
        case .succes(let data):
            let decoder = JSONDecoder()
            do {
                movies = try decoder.decode([Movie].self, from: data as! Data)
            } catch {
                print(error)
            }
        }
    }

    @objc private func presentAddMovie() {
        let storyboard = UIStoryboard(name: "AddMovie", bundle: Bundle.main)
        guard let viewController = storyboard.instantiateInitialViewController() else { return }
        present(viewController, animated: true, completion: nil)
    }
}

extension MoviesListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MoviesListCellIdentifier",
                                                 for: indexPath)
        let movie = movies[indexPath.row]
        cell.textLabel?.text = movie.title
        cell.detailTextLabel?.text = movie.details
        return cell
    }
}
