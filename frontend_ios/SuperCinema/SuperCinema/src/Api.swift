//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

// MARK: - Result

enum Result<T> {
    case succes(T)
    case error(Error)
}

typealias ResultBlock = (Result<Any>) -> Void

// MARK: - Api

struct Api {
    var getMovies = getMovies(completion:)
}

// MARK: - Private

private func getMovies(completion: @escaping ResultBlock) {
    URLSession
        .shared
        .dataTask(with: urlRequest(), completionHandler: handler(with: completion))
        .resume()
}

fileprivate func urlRequest() -> URLRequest {
    let url = URL(string: "http://localhost:8080/movie")!
    return URLRequest(url: url)
}

fileprivate func handler(with c: @escaping ResultBlock)
    -> (_ data: Data?, _ response: URLResponse?, _ error: Error?) -> Void {
        return { (d, r, e) in
            responseHandler(data: d, response: r, error: e, completion: c)
        }
}

fileprivate func responseHandler(data: Data?,
                                 response: URLResponse?,
                                 error: Error?,
                                 completion: ResultBlock) {
    if let error = error  {
        defer { completion(.error(error)) }
        return
    }

    completion(.succes(data as Any))
}
