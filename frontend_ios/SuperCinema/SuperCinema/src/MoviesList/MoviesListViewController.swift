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
        let url = URL(string: "http://localhost:8080/movie")!
        task = URLSession.shared.dataTask(with: url,
                                          completionHandler: { [weak self] (data, response, error) in
                                            self?.handleMoviesResponse(data: data, response: response, error: error)
        })
        task?.resume()
    }

    private func handleMoviesResponse(data: Data?, response: URLResponse?, error: Error?) {
        if let data = data {
            let decoder = JSONDecoder()
            do {
                movies = try decoder.decode([Movie].self, from: data)
            } catch {
                print(error)
            }
        }
        if let error = error {
            print(error)
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
        return cell
    }
}

struct Movie: Codable {
    let id: Int
    let title: String
    let duration: Int
    let productionCountry: String?
    let productionYear: String?
    let directors: [Crew]
    let cast: [Crew]?
    let movieShow: String?
    let types: [String]
}

struct Crew: Codable {
}

